package com.example.model.article.vo;

import com.example.model.article.pojo.ApArticle;
import lombok.Data;

@Data
public class HotArticleVo extends ApArticle {
    /**
     * 文章分值
     */
    private Integer score;
}