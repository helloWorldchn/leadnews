package com.example.wemedia.controller.v1;

import com.example.model.common.dto.PageResponseResult;
import com.example.model.common.dto.ResponseResult;
import com.example.model.wemedia.dto.StatisticsDto;
import com.example.wemedia.service.WmStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/statistics")
public class WmStatisticsController {

    @Autowired
    private WmStatisticsService wmStatisticsService;

    @GetMapping("/newsDimension")
    public ResponseResult newsDimension(String beginDate, String endDate){
        System.out.println("beginDate "+beginDate+" endDate "+endDate);
        return wmStatisticsService.newsDimension(beginDate,endDate);
    }

    @GetMapping("/newsPage")
    public PageResponseResult newsPage(StatisticsDto dto){
        System.out.println("dto "+dto);
        return wmStatisticsService.newsPage(dto);
    }
}
