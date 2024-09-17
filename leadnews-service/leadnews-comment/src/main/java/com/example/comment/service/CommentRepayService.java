package com.example.comment.service;

import com.example.model.comment.dto.CommentRepayDto;
import com.example.model.comment.dto.CommentRepayLikeDto;
import com.example.model.comment.dto.CommentRepaySaveDto;
import com.example.model.common.dto.ResponseResult;

/**
 * 评论回复
 */
public interface CommentRepayService {

    /**
     * 查看更多回复内容
     * @param dto
     * @return
     */
    public ResponseResult loadCommentRepay(CommentRepayDto dto);

    /**
     * 保存回复
     * @return
     */
    public ResponseResult saveCommentRepay(CommentRepaySaveDto dto);

    /**
     * 点赞回复的评论
     * @param dto
     * @return
     */
    public ResponseResult saveCommentRepayLike(CommentRepayLikeDto dto);
}