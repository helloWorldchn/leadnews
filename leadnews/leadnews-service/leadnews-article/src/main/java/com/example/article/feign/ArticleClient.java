package com.example.article.feign;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.api.article.IArticleClient;
import com.example.article.service.ApArticleConfigService;
import com.example.article.service.ApArticleService;
import com.example.model.article.dto.ArticleCommentDto;
import com.example.model.article.dto.ArticleDto;
import com.example.model.article.pojo.ApArticleConfig;
import com.example.model.comment.dto.CommentConfigDto;
import com.example.model.common.dto.PageResponseResult;
import com.example.model.common.dto.ResponseResult;
import com.example.model.wemedia.dto.StatisticsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class ArticleClient implements IArticleClient {

    @Autowired
    private ApArticleService apArticleService;

    @Override
    @PostMapping("/api/v1/article/save")
    public ResponseResult saveArticle(@RequestBody ArticleDto dto) {
        return apArticleService.saveArticle(dto);
    }

    @Autowired
    private ApArticleConfigService apArticleConfigService;

    @GetMapping("/api/v1/article/findArticleConfigByArticleId/{articleId}")
    @Override
    public ResponseResult findArticleConfigByArticleId(@PathVariable("articleId") Long articleId) {
        ApArticleConfig apArticleConfig = apArticleConfigService.getOne(Wrappers.<ApArticleConfig>lambdaQuery().eq(ApArticleConfig::getArticleId, articleId));
        return ResponseResult.okResult(apArticleConfig);
    }

    @PostMapping("/api/v1/article/findNewsComments")
    @Override
    public PageResponseResult findNewsComments(@RequestBody ArticleCommentDto dto) {
        return apArticleService.findNewsComments(dto);
    }

    @PostMapping("/api/v1/article/updateCommentStatus")
    @Override
    public ResponseResult updateCommentStatus(@RequestBody CommentConfigDto dto) {
        return apArticleConfigService.updateCommentStatus(dto);
    }

    @GetMapping("/api/v1/article/queryLikesAndCollections")
    @Override
    public ResponseResult queryLikesAndCollections(@RequestParam("wmUserId") Integer wmUserId, @RequestParam("beginDate") Date beginDate, @RequestParam("endDate") Date endDate) {
        return apArticleService.queryLikesAndCollections(wmUserId,beginDate,endDate);
    }

    @PostMapping("/api/v1/article/newPage")
    @Override
    public PageResponseResult newPage(@RequestBody StatisticsDto dto) {
        return apArticleService.newPage(dto);
    }
}