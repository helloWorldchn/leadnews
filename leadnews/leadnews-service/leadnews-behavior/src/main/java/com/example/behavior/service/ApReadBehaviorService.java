package com.example.behavior.service;

import com.example.model.behavior.dto.ReadBehaviorDto;
import com.example.model.common.dto.ResponseResult;

public interface ApReadBehaviorService {

    /**
     * 保存阅读行为
     * @param dto
     * @return
     */
    public ResponseResult readBehavior(ReadBehaviorDto dto);
}
