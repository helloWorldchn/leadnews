package com.example.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.common.dto.ResponseResult;
import com.example.model.wemedia.dto.WmLoginDto;
import com.example.model.wemedia.pojo.WmUser;

public interface WmUserService extends IService<WmUser> {

    /**
     * 自媒体端登录
     * @param dto
     * @return
     */
    public ResponseResult login(WmLoginDto dto);

}