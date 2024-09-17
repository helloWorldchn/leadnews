package com.example.search.controller.v1;

import com.example.model.common.dto.ResponseResult;
import com.example.model.search.dto.HistorySearchDto;
import com.example.search.service.ApUserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/history")
public class ApUserSearchController {

    @Autowired
    private ApUserSearchService apUserSearchService;

    // 加载历史搜索记录
    @PostMapping("/load")
    public ResponseResult findUserSearch(){
        return apUserSearchService.findUserSearch();
    }

    // 删除历史搜索记录
    @PostMapping("/del")
    public ResponseResult delUserSearch(@RequestBody HistorySearchDto dto){
        return apUserSearchService.delUserSearch(dto);
    }
}
