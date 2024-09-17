package com.example.model.wemedia.vo;

import com.example.model.wemedia.pojo.WmNews;
import lombok.Data;

@Data
public class WmNewsVo extends WmNews {
    /**
     * 作者名称
     */
    private String authorName;
}