package com.example.behavior.service;

import com.example.model.behavior.dto.LikesBehaviorDto;
import com.example.model.common.dto.ResponseResult;

public interface ApLikesBehaviorService {

    /**
     * 存储喜欢数据
     * @param dto
     * @return
     */
    public ResponseResult like(LikesBehaviorDto dto);
}
