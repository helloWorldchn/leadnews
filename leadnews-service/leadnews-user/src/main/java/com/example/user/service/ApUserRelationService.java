package com.example.user.service;


import com.example.model.common.dto.ResponseResult;
import com.example.model.user.dto.UserRelationDto;


public interface ApUserRelationService {
    /**
     * 用户关注/取消关注
     * @param dto
     * @return
     */
    public ResponseResult follow(UserRelationDto dto);
}