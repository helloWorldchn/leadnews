package com.example.wemedia.service;

import com.example.model.common.dto.PageResponseResult;
import com.example.model.common.dto.ResponseResult;
import com.example.model.wemedia.dto.StatisticsDto;

import java.util.Date;

public interface WmStatisticsService {

    /**
     * 图文统计
     * @param beginDate
     * @param endDate
     * @return
     */
    public ResponseResult newsDimension(String beginDate, String endDate);

    /**
     * 分页查询图文统计
     * @param dto
     * @return
     */
    PageResponseResult newsPage(StatisticsDto dto);
}
