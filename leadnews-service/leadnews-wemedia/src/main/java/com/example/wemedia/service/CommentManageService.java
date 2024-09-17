package com.example.wemedia.service;

import com.example.model.article.dto.ArticleCommentDto;
import com.example.model.comment.dto.CommentConfigDto;
import com.example.model.comment.dto.CommentLikeDto;
import com.example.model.comment.dto.CommentManageDto;
import com.example.model.comment.dto.CommentRepaySaveDto;
import com.example.model.common.dto.PageResponseResult;
import com.example.model.common.dto.ResponseResult;
import org.springframework.web.bind.annotation.RequestBody;

public interface CommentManageService {

    /**
     * 查看文章评论列表
     * @param dto
     * @return
     */
    public PageResponseResult findNewsComments(ArticleCommentDto dto);

    /**
     * 打开或关闭评论
     * @param dto
     * @return
     */
    public ResponseResult updateCommentStatus(CommentConfigDto dto);

    /**
     * 查询评论列表
     * @return
     */
    public ResponseResult list(CommentManageDto dto);

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    public ResponseResult delComment(String commentId);

    /**
     * 删除评论回复
     * @param commentRepayId
     * @return
     */
    public ResponseResult delCommentRepay(String commentRepayId);

    /**
     * 回复评论
     * @param dto
     * @return
     */
    public ResponseResult saveCommentRepay(CommentRepaySaveDto dto);

    /**
     * 点赞
     * @param dto
     * @return
     */
    public ResponseResult like(CommentLikeDto dto);
}
