package com.example.model.article.dto;

import com.example.model.common.dto.PageRequestDto;
import lombok.Data;

@Data
public class ArticleCommentDto extends PageRequestDto {
    private String beginDate;
    private String endDate;
    private Integer wmUserId;
}
