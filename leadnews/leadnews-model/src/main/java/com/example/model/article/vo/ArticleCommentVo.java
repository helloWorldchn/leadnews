package com.example.model.article.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleCommentVo {

    private Long id;

    private String title;

    private Integer comments;

    private Boolean isComment;

    private Date publishTime;
}
