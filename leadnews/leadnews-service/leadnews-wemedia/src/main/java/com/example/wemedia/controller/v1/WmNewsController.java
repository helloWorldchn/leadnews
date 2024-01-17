package com.example.wemedia.controller.v1;

import com.example.common.constants.WemediaConstants;
import com.example.model.common.dto.ResponseResult;
import com.example.model.wemedia.dto.NewsAuthDto;
import com.example.model.wemedia.dto.WmNewsDto;
import com.example.model.wemedia.dto.WmNewsPageReqDto;
import com.example.wemedia.service.WmNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/news")
public class WmNewsController {

    @Autowired
    private WmNewsService wmNewsService;

    @PostMapping("/list")
    public ResponseResult findList(@RequestBody WmNewsPageReqDto dto){
        return wmNewsService.findList(dto);
    }

    @PostMapping("/submit")
    public ResponseResult submitNews(@RequestBody WmNewsDto dto){
        return wmNewsService.submitNews(dto);
    }

    // 修改文章的回显
    @GetMapping("/one/{id}")
    public ResponseResult findOne(@PathVariable("id") Integer id){
        return wmNewsService.findOne(id);
    }

    // 商品上下架
    @PostMapping("/down_or_up")
    public ResponseResult downOrUp(@RequestBody WmNewsDto dto){
        return wmNewsService.downOrUp(dto);
    }

    @PostMapping("/list_vo")
    public ResponseResult findList(@RequestBody NewsAuthDto dto){
        return wmNewsService.findList(dto);
    }

    @GetMapping("/one_vo/{id}")
    public ResponseResult findWmNewsVo(@PathVariable("id") Integer id){
        return wmNewsService.findWmNewsVo(id);
    }

    @PostMapping("/auth_pass")
    public ResponseResult authPass(@RequestBody NewsAuthDto dto){
        return wmNewsService.updateStatus(WemediaConstants.WM_NEWS_AUTH_PASS,dto);
    }

    @PostMapping("/auth_fail")
    public ResponseResult authFail(@RequestBody NewsAuthDto dto){
        return wmNewsService.updateStatus(WemediaConstants.WM_NEWS_AUTH_FAIL,dto);
    }
}