package com.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.admin.dto.AdUserDto;
import com.example.model.admin.pojo.AdUser;
import com.example.model.common.dto.ResponseResult;

public interface AdUserService extends IService<AdUser> {

    /**
     * 登录
     * @param dto
     * @return
     */
    public ResponseResult login(AdUserDto dto);
}
