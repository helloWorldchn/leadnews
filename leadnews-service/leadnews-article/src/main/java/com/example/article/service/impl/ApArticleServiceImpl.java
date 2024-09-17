package com.example.article.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.article.mapper.ApArticleConfigMapper;
import com.example.article.mapper.ApArticleContentMapper;
import com.example.article.mapper.ApArticleMapper;
import com.example.article.service.ApArticleService;
import com.example.article.service.ArticleFreemarkerService;
import com.example.common.constants.ArticleConstants;
import com.example.common.constants.BehaviorConstants;
import com.example.common.redis.CacheService;
import com.example.model.article.dto.ArticleCommentDto;
import com.example.model.article.dto.ArticleDto;
import com.example.model.article.dto.ArticleHomeDto;

import com.example.model.article.dto.ArticleInfoDto;
import com.example.model.article.pojo.ApArticle;
import com.example.model.article.pojo.ApArticleConfig;
import com.example.model.article.pojo.ApArticleContent;
import com.example.model.article.vo.ArticleCommentVo;
import com.example.model.article.vo.HotArticleVo;
import com.example.model.common.dto.PageResponseResult;
import com.example.model.common.dto.ResponseResult;
import com.example.model.common.enums.AppHttpCodeEnum;
import com.example.model.message.ArticleVisitStreamMessage;
import com.example.model.user.pojo.ApUser;
import com.example.model.wemedia.dto.StatisticsDto;
import com.example.utils.common.DateUtils;
import com.example.utils.thread.AppThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Wrapper;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
public class ApArticleServiceImpl extends ServiceImpl<ApArticleMapper, ApArticle> implements ApArticleService {

    // 单页最大加载的数字
    private final static short MAX_PAGE_SIZE = 50;

    @Autowired
    private ApArticleMapper apArticleMapper;

    @Autowired
    private ApArticleConfigMapper apArticleConfigMapper;

    @Autowired
    private ApArticleContentMapper apArticleContentMapper;

    @Autowired
    private ArticleFreemarkerService articleFreemarkerService;

    /**
     * 根据参数加载文章列表
     * @param loadtype 1为加载更多  2为加载最新
     * @param dto
     * @return
     */
    @Override
    public ResponseResult load(ArticleHomeDto dto, Short loadtype) {
        // 1.校验参数
        Integer size = dto.getSize();
        if(size == null || size == 0){
            size = 10;
        }
        size = Math.min(size,MAX_PAGE_SIZE);
        dto.setSize(size);

        // 校验类型参数
        if(!loadtype.equals(ArticleConstants.LOADTYPE_LOAD_MORE)&&!loadtype.equals(ArticleConstants.LOADTYPE_LOAD_NEW)){
            loadtype = ArticleConstants.LOADTYPE_LOAD_MORE;
        }
        // 校验文章频道
        if(StringUtils.isEmpty(dto.getTag())){
            dto.setTag(ArticleConstants.DEFAULT_TAG);
        }

        // 校验时间
        if(dto.getMaxBehotTime() == null) dto.setMaxBehotTime(new Date());
        if(dto.getMinBehotTime() == null) dto.setMinBehotTime(new Date());
        // 2.查询数据
        List<ApArticle> apArticles = apArticleMapper.loadArticleList(dto, loadtype);

        // 3.结果封装
        ResponseResult responseResult = ResponseResult.okResult(apArticles);
        return responseResult;
    }

    /**
     * 根据参数加载文章列表
     *
     * @param dto
     * @param type 1为加载更多  2为加载最新
     * @return
     */
    @Override
    public ResponseResult load2(ArticleHomeDto dto, Short type, boolean firstPage) {
        if(firstPage){
            String jsonStr = cacheService.get(ArticleConstants.HOT_ARTICLE_FIRST_PAGE + dto.getTag());
            if(StringUtils.isNotBlank(jsonStr)){
                List<HotArticleVo> hotArticleVoList = JSON.parseArray(jsonStr, HotArticleVo.class);
                ResponseResult responseResult = ResponseResult.okResult(hotArticleVoList);
                return responseResult;
            }
        }
        return load(dto, type);
    }

    @Override
    public ResponseResult saveArticle(ArticleDto dto) {
        // 测试服务降级
        //try {
        //    Thread.sleep(3000);
        //} catch (InterruptedException e) {
        //    throw new RuntimeException(e);
        //}
        // 1.检查参数
        if (dto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        ApArticle apArticle = new ApArticle();
        BeanUtils.copyProperties(dto, apArticle);
        // 2.检查是否存在id
        if (dto.getId() == null) {
            // 2.1如果不存在id，保存：文章、文章配置、文章内容
            save(apArticle); // 保存文章

            ApArticleConfig apArticleConfig = new ApArticleConfig(apArticle.getId()); // 通过新的构造方法设置几个默认属性
            apArticleConfigMapper.insert(apArticleConfig); // 保存文章配置

            ApArticleContent apArticleContent = new ApArticleContent();
            apArticleContent.setArticleId(apArticle.getId());
            apArticleContent.setContent(dto.getContent());
            apArticleContentMapper.insert(apArticleContent); // 保存文章内容
        } else {
            // 2.如果存在id，修改文章、文章内容
            updateById(apArticle); // 修改文章
            ApArticleContent apArticleContent = apArticleContentMapper.selectOne(Wrappers.<ApArticleContent>lambdaQuery().eq(ApArticleContent::getArticleId, dto.getId()));
            apArticleContent.setContent(dto.getContent());
            apArticleContentMapper.updateById(apArticleContent); // 修改文章内容
        }
        //异步调用 生成静态文件上传到minio中
        articleFreemarkerService.buildArticleToMinIO(apArticle,dto.getContent());

        // 3.结果返回文章id
        return ResponseResult.okResult(apArticle.getId());
    }

    @Autowired
    private CacheService cacheService;

    /**
     * 加载文章详情 数据回显
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult loadArticleBehavior(ArticleInfoDto dto) {
        //0.检查参数
        if (dto == null || dto.getArticleId() == null || dto.getAuthorId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //{ "isfollow": true, "islike": true,"isunlike": false,"iscollection": true }
        boolean isfollow = false, islike = false, isunlike = false, iscollection = false;

        ApUser user = AppThreadLocalUtil.getUser();
        if(user != null){
            //喜欢行为
            String likeBehaviorJson = (String) cacheService.hGet(BehaviorConstants.LIKE_BEHAVIOR + dto.getArticleId().toString(), user.getId().toString());
            if(StringUtils.isNotBlank(likeBehaviorJson)){
                islike = true;
            }
            //不喜欢的行为
            String unLikeBehaviorJson = (String) cacheService.hGet(BehaviorConstants.UN_LIKE_BEHAVIOR + dto.getArticleId().toString(), user.getId().toString());
            if(StringUtils.isNotBlank(unLikeBehaviorJson)){
                isunlike = true;
            }
            //是否收藏
            String collectionJson = (String) cacheService.hGet(BehaviorConstants.COLLECTION_BEHAVIOR+user.getId(),dto.getArticleId().toString());
            if(StringUtils.isNotBlank(collectionJson)){
                iscollection = true;
            }

            //是否关注
            Double score = cacheService.zScore(BehaviorConstants.APUSER_FOLLOW_RELATION + user.getId(), dto.getAuthorId().toString());
            System.out.println(score);
            if(score != null){
                isfollow = true;
            }
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("isfollow", isfollow);
        resultMap.put("islike", islike);
        resultMap.put("isunlike", isunlike);
        resultMap.put("iscollection", iscollection);

        return ResponseResult.okResult(resultMap);
    }

    /**
     * 更新文章的分值  同时更新缓存中的热点文章数据
     *
     * @param message
     */
    @Override
    public void updateScore(ArticleVisitStreamMessage message) {
        //1.更新文章的阅读、点赞、收藏、评论的数量
        ApArticle apArticle = updateArticle(message);
        //2.计算文章的分值
        Integer score = computeScore(apArticle);
        score = score * 3;
        //3.替换当前文章对应频道的热点数据
        replaceDataToRedis(apArticle, score, ArticleConstants.HOT_ARTICLE_FIRST_PAGE + apArticle.getChannelId());
        //4.替换推荐对应的热点数据
        replaceDataToRedis(apArticle, score, ArticleConstants.HOT_ARTICLE_FIRST_PAGE + ArticleConstants.DEFAULT_TAG);
    }

    /**
     * 查询文章评论统计
     *
     * @param dto
     * @return
     */
    @Override
    public PageResponseResult findNewsComments(ArticleCommentDto dto) {
        Integer currentPage = dto.getPage();
        dto.setPage((dto.getPage()-1)*dto.getSize());
        List<ArticleCommentVo> list = apArticleMapper.findNewsComments(dto);
        int count = apArticleMapper.findNewsCommentsCount(dto);

        PageResponseResult responseResult = new PageResponseResult(currentPage, dto.getSize(), count);
        responseResult.setData(list);
        return responseResult;
    }

    /**
     * 图文统计统计
     *
     * @param wmUserId
     * @param beginDate
     * @param endDate
     * @return
     */
    @Override
    public ResponseResult queryLikesAndCollections(Integer wmUserId, Date beginDate, Date endDate) {
        Map map = apArticleMapper.queryLikesAndCollections(wmUserId, beginDate, endDate);
        return ResponseResult.okResult(map);
    }

    /**
     * 分页查询 图文统计
     * @param dto
     * @return
     */
    @Override
    public PageResponseResult newPage(StatisticsDto dto) {

        //类型转换
        Date beginDate = DateUtils.stringToDate(dto.getBeginDate());
        Date endDate = DateUtils.stringToDate(dto.getEndDate());
        //检查参数
        dto.checkParam();
        //分页查询
        IPage page = new Page(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<ApArticle> lambdaQueryWrapper = Wrappers.<ApArticle>lambdaQuery()
                .eq(ApArticle::getAuthorId, dto.getWmUserId())
                .between(ApArticle::getPublishTime,beginDate, endDate)
                .select(ApArticle::getId,ApArticle::getTitle,ApArticle::getLikes,ApArticle::getCollection,ApArticle::getComment,ApArticle::getViews);

        lambdaQueryWrapper.orderByDesc(ApArticle::getPublishTime);

        page = page(page,lambdaQueryWrapper);
        PageResponseResult responseResult = new PageResponseResult(dto.getPage(),dto.getSize(),(int)page.getTotal());
        responseResult.setData(page.getRecords());
        return responseResult;
    }

    /**
     * 替换数据并且存入到redis
     * @param apArticle
     * @param score
     * @param s
     */
    private void replaceDataToRedis(ApArticle apArticle, Integer score, String s) {
        String articleListStr = cacheService.get(s);
        if (StringUtils.isNotBlank(articleListStr)) {
            List<HotArticleVo> hotArticleVoList = JSON.parseArray(articleListStr, HotArticleVo.class);
            boolean flag = true;
            //如果缓存中存在该文章，只更新分值
            for (HotArticleVo hotArticleVo : hotArticleVoList) {
                if (hotArticleVo.getId().equals(apArticle.getId())) {
                    hotArticleVo.setScore(score);
                    flag = false;
                    break;
                }
            }
            //如果缓存中不存在，查询缓存中分值最小的一条数据，进行分值的比较，如果当前文章的分值大于缓存中的数据，就替换
            if (flag) {
                if (hotArticleVoList.size() >= 30) {
                    hotArticleVoList = hotArticleVoList.stream().sorted(Comparator.comparing(HotArticleVo::getScore).reversed()).collect(Collectors.toList());
                    HotArticleVo lastHot = hotArticleVoList.get(hotArticleVoList.size() - 1);
                    if (lastHot.getScore() < score) {
                        hotArticleVoList.remove(lastHot);
                        HotArticleVo hot = new HotArticleVo();
                        BeanUtils.copyProperties(apArticle, hot);
                        hot.setScore(score);
                        hotArticleVoList.add(hot);
                    }
                } else {
                    HotArticleVo hot = new HotArticleVo();
                    BeanUtils.copyProperties(apArticle, hot);
                    hot.setScore(score);
                    hotArticleVoList.add(hot);
                }
            }
            //缓存到redis
            hotArticleVoList = hotArticleVoList.stream().sorted(Comparator.comparing(HotArticleVo::getScore).reversed()).collect(Collectors.toList());
            cacheService.set(s, JSON.toJSONString(hotArticleVoList));
        }
    }

    /**
     * 更新文章行为数量
     * @param message
     */
    private ApArticle updateArticle(ArticleVisitStreamMessage message) {
        ApArticle apArticle = getById(message.getArticleId());
        apArticle.setCollection(apArticle.getCollection() == null ? 0 : apArticle.getCollection() + message.getCollect());
        apArticle.setComment(apArticle.getComment() == null ? 0 : apArticle.getComment() + message.getComment());
        apArticle.setLikes(apArticle.getLikes() == null ? 0 : apArticle.getLikes() + message.getLike());
        apArticle.setViews(apArticle.getViews() == null ? 0 : apArticle.getViews() + message.getView());
        updateById(apArticle);
        return apArticle;
    }

    /**
     * 计算文章的具体分值
     * @param apArticle
     * @return
     */
    private Integer computeScore(ApArticle apArticle) {
        Integer score = 0;
        if (apArticle.getLikes() != null) {
            score += apArticle.getLikes() * ArticleConstants.HOT_ARTICLE_LIKE_WEIGHT;
        }
        if (apArticle.getViews() != null) {
            score += apArticle.getViews();
        }
        if (apArticle.getComment() != null) {
            score += apArticle.getComment() * ArticleConstants.HOT_ARTICLE_COMMENT_WEIGHT;
        }
        if (apArticle.getCollection() != null) {
            score += apArticle.getCollection() * ArticleConstants.HOT_ARTICLE_COLLECTION_WEIGHT;
        }
        return score;
    }
}