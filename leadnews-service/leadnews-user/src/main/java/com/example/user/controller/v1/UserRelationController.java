package com.example.user.controller.v1;

import com.example.model.common.dto.ResponseResult;
import com.example.model.user.dto.UserRelationDto;
import com.example.user.service.ApUserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserRelationController {

    @Autowired
    private ApUserRelationService apUserRelationService;

    // 用户关注功能
    @PostMapping("/user_follow")
    public ResponseResult follow(@RequestBody UserRelationDto dto){
        return apUserRelationService.follow(dto);
    }
}