package com.example.model.wemedia.dto;

import com.example.model.common.dto.PageRequestDto;
import lombok.Data;

@Data
public class SensitiveDto extends PageRequestDto {

    /**
     * 敏感词名称
     */
    private String name;
}
