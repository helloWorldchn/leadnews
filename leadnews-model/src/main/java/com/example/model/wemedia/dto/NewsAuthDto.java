package com.example.model.wemedia.dto;

import com.example.model.common.dto.PageRequestDto;
import lombok.Data;

@Data
public class NewsAuthDto extends PageRequestDto {

    /**
     * 文章标题
     */
    private String title;
    /**
     * 状态
     */
    private Short status;
    /**
     * 自媒体文章id
     */
    private Integer id;
    /**
     * 审核失败的原因
     */
    private String msg;
}