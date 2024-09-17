package com.example.admin.controller.v1;

import com.example.admin.service.AdUserService;
import com.example.model.admin.dto.AdUserDto;
import com.example.model.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 管理员模块的用户登录
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AdUserService adUserService;

    @PostMapping("/in")
    public ResponseResult login(@RequestBody AdUserDto dto){
        return adUserService.login(dto);
    }
}
