package com.example.behavior.controller.v1;

import com.example.behavior.service.ApReadBehaviorService;
import com.example.model.behavior.dto.ReadBehaviorDto;
import com.example.model.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/read_behavior")
public class ApReadBehaviorController {

    @Autowired
    private ApReadBehaviorService apReadBehaviorService;

    @PostMapping
    public ResponseResult readBehavior(@RequestBody ReadBehaviorDto dto) {
        return apReadBehaviorService.readBehavior(dto);
    }
}
