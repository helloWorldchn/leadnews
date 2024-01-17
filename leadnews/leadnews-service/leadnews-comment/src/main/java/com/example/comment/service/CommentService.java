package com.example.comment.service;

import com.example.model.comment.dto.CommentDto;
import com.example.model.comment.dto.CommentLikeDto;
import com.example.model.comment.dto.CommentSaveDto;
import com.example.model.common.dto.ResponseResult;
import org.springframework.web.bind.annotation.RequestBody;

public interface CommentService {

    /**
     * 保存评论
     * @param dto
     * @return
     */
    public ResponseResult saveComment(CommentSaveDto dto);

    /**
     * 点赞
     * @param dto
     * @return
     */
    public ResponseResult like(CommentLikeDto dto);

    /**
     * 加载评论列表
     * @param dto
     * @return
     */
    public ResponseResult findByArticleId(CommentDto dto);


}
