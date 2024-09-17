package com.example.behavior.service;

import com.example.model.behavior.dto.UnLikesBehaviorDto;
import com.example.model.common.dto.ResponseResult;

/**
 * <p>
 * APP不喜欢行为表 服务类
 * </p>
 *
 * @author itheima
 */
public interface ApUnlikesBehaviorService {

    /**
     * 不喜欢
     * @param dto
     * @return
     */
    public ResponseResult unLike(UnLikesBehaviorDto dto);

}