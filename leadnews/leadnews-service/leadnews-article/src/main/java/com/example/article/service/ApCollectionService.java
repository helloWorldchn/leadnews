package com.example.article.service;

import com.example.model.article.dto.CollectionBehaviorDto;
import com.example.model.common.dto.ResponseResult;

public interface ApCollectionService {

    /**
     * 收藏
     * @param dto
     * @return
     */
    public ResponseResult collection(CollectionBehaviorDto dto);
}
