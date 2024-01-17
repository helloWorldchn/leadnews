package com.example.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.common.dto.ResponseResult;
import com.example.model.user.dto.LoginDto;
import com.example.model.user.pojo.ApUser;

public interface ApUserService extends IService<ApUser>{

    /**
     * app端登录
     * @param dto
     * @return
     */
    public ResponseResult login(LoginDto dto);

}
