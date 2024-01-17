package com.example.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.article.dto.ArticleCommentDto;
import com.example.model.article.dto.ArticleDto;
import com.example.model.article.dto.ArticleHomeDto;
import com.example.model.article.dto.ArticleInfoDto;
import com.example.model.article.pojo.ApArticle;
import com.example.model.common.dto.PageResponseResult;
import com.example.model.common.dto.ResponseResult;
import com.example.model.message.ArticleVisitStreamMessage;
import com.example.model.wemedia.dto.StatisticsDto;

import java.io.IOException;
import java.util.Date;

public interface ApArticleService extends IService<ApArticle> {

    /**
     * 根据参数加载文章列表
     * @param loadtype 1为加载更多  2为加载最新
     * @param dto
     * @return
     */
    ResponseResult load(ArticleHomeDto dto, Short loadtype);

    /**
     * 加载文章列表
     * @param dto
     * @param type  1 加载更多   2 加载最新
     * @param firstPage  true  是首页  false 非首页
     * @return
     */
    public ResponseResult load2(ArticleHomeDto dto, Short type, boolean firstPage);

    /**
     * 保存app端相关文章
     * @param dto
     * @return
     */
    ResponseResult saveArticle(ArticleDto dto);

    /**
     * 加载文章详情 数据回显
     * @param dto
     * @return
     */
    public ResponseResult loadArticleBehavior(ArticleInfoDto dto);

    /**
     * 更新文章的分值  同时更新缓存中的热点文章数据
     * @param message
     */
    public void updateScore(ArticleVisitStreamMessage message);

    /**
     * 查询文章评论统计
     * @param dto
     * @return
     */
    public PageResponseResult findNewsComments(ArticleCommentDto dto);

    /**
     * 图文统计统计
     * @param wmUserId
     * @param beginDate
     * @param endDate
     * @return
     */
    ResponseResult queryLikesAndCollections(Integer wmUserId, Date beginDate, Date endDate);

    /**
     * 分页查询 图文统计
     * @param dto
     * @return
     */
    PageResponseResult newPage(StatisticsDto dto);
}