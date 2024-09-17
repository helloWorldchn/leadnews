package com.example.api.article.fallback;

import com.example.api.article.IArticleClient;
import com.example.model.article.dto.ArticleCommentDto;
import com.example.model.article.dto.ArticleDto;
import com.example.model.comment.dto.CommentConfigDto;
import com.example.model.common.dto.PageResponseResult;
import com.example.model.common.dto.ResponseResult;
import com.example.model.common.enums.AppHttpCodeEnum;
import com.example.model.wemedia.dto.StatisticsDto;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * feign失败配置
 * @author itheima
 */
@Component
public class IArticleClientFallback implements IArticleClient {
    @Override
    public ResponseResult saveArticle(ArticleDto dto)  {
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR,"获取数据失败");
    }

    @Override
    public ResponseResult findArticleConfigByArticleId(Long articleId) {
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR,"获取数据失败");
    }

    @Override
    public PageResponseResult findNewsComments(ArticleCommentDto dto) {
        PageResponseResult responseResult = new PageResponseResult(dto.getPage(),dto.getSize(),0);
        responseResult.setCode(501);
        responseResult.setErrorMessage("获取数据失败");
        return responseResult;
    }

    @Override
    public ResponseResult updateCommentStatus(CommentConfigDto dto) {
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR,"获取数据失败");
    }

    @Override
    public ResponseResult queryLikesAndCollections(Integer wmUserId, Date beginDate, Date endDate) {
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR,"获取数据失败");
    }

    @Override
    public PageResponseResult newPage(StatisticsDto dto) {
        PageResponseResult responseResult = new PageResponseResult(dto.getPage(),dto.getSize(),0);
        responseResult.setCode(501);
        responseResult.setErrorMessage("获取数据失败");
        return responseResult;
    }

}
