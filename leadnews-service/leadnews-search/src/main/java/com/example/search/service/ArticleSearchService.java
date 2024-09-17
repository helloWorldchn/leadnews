package com.example.search.service;

import com.example.model.common.dto.ResponseResult;
import com.example.model.search.dto.UserSearchDto;

import java.io.IOException;

public interface ArticleSearchService {

    /**
     * es文章分页检索
     * @param dto
     * @return
     */
    public ResponseResult search(UserSearchDto dto) throws IOException;
}
