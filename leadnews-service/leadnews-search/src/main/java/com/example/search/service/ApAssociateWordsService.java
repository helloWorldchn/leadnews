package com.example.search.service;

import com.example.model.common.dto.ResponseResult;
import com.example.model.search.dto.UserSearchDto;

public interface ApAssociateWordsService {

    /**
     * 搜索联想词
     * @param dto
     * @return
     */
    public ResponseResult search(UserSearchDto dto);
}
