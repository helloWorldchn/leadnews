package com.example.api.article;

import com.example.api.article.fallback.IArticleClientFallback;
import com.example.model.article.dto.ArticleCommentDto;
import com.example.model.article.dto.ArticleDto;
import com.example.model.comment.dto.CommentConfigDto;
import com.example.model.common.dto.PageResponseResult;
import com.example.model.common.dto.ResponseResult;
import com.example.model.wemedia.dto.StatisticsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@FeignClient(value = "leadnews-article", fallback = IArticleClientFallback.class)
public interface IArticleClient {

    @PostMapping("/api/v1/article/save")
    public ResponseResult saveArticle(@RequestBody ArticleDto dto);

    @GetMapping("/api/v1/article/findArticleConfigByArticleId/{articleId}")
    ResponseResult findArticleConfigByArticleId(@PathVariable("articleId") Long articleId);

    @PostMapping("/api/v1/article/findNewsComments")
    public PageResponseResult findNewsComments(@RequestBody ArticleCommentDto dto);

    @PostMapping("/api/v1/article/updateCommentStatus")
    public ResponseResult updateCommentStatus(@RequestBody CommentConfigDto dto);

    @GetMapping("/api/v1/article/queryLikesAndCollections")
    ResponseResult queryLikesAndCollections(@RequestParam("wmUserId") Integer wmUserId, @RequestParam("beginDate") Date beginDate, @RequestParam("endDate") Date endDate);

    @PostMapping("/api/v1/article/newPage")
    PageResponseResult newPage(@RequestBody StatisticsDto dto);

}