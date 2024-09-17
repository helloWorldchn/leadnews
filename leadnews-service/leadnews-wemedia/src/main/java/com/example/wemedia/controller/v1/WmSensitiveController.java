package com.example.wemedia.controller.v1;

import com.example.model.common.dto.ResponseResult;
import com.example.model.wemedia.dto.SensitiveDto;
import com.example.model.wemedia.pojo.WmSensitive;
import com.example.wemedia.service.WmSensitiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员模块的敏感词管理
 */
@RestController
@RequestMapping("/api/v1/sensitive")
public class WmSensitiveController {

    @Autowired
    private WmSensitiveService wmSensitiveService;

    @PostMapping("/list")
    public ResponseResult list(@RequestBody SensitiveDto dto){
        return wmSensitiveService.list(dto);
    }

    @PostMapping("/save")
    public ResponseResult insert(@RequestBody WmSensitive wmSensitive){
        return wmSensitiveService.insert(wmSensitive);
    }

    @PostMapping("/update")
    public ResponseResult update(@RequestBody WmSensitive wmSensitive){
        return wmSensitiveService.update(wmSensitive);
    }

    @DeleteMapping("/del/{id}")
    public ResponseResult delete(@PathVariable("id") Integer id){
        return wmSensitiveService.delete(id);
    }
}
