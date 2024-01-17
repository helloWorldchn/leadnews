package com.example.model.comment.dto;

import com.example.model.common.dto.PageRequestDto;
import lombok.Data;

@Data
public class CommentManageDto extends PageRequestDto {

    /**
     * 文章id
     */
    private Long articleId;
}
