package com.example.model.wemedia.dto;

import com.example.model.common.dto.PageRequestDto;
import lombok.Data;

import java.util.Date;

@Data
public class StatisticsDto extends PageRequestDto {

    private String beginDate;
    private String endDate;
    private Integer wmUserId;
}
