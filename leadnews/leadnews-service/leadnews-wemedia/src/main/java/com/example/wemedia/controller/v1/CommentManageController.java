package com.example.wemedia.controller.v1;

import com.example.api.article.IArticleClient;
import com.example.model.article.dto.ArticleCommentDto;
import com.example.model.comment.dto.CommentConfigDto;
import com.example.model.comment.dto.CommentLikeDto;
import com.example.model.comment.dto.CommentManageDto;
import com.example.model.comment.dto.CommentRepaySaveDto;
import com.example.model.common.dto.PageResponseResult;
import com.example.model.common.dto.ResponseResult;
import com.example.model.wemedia.pojo.WmUser;
import com.example.utils.thread.WmThreadLocalUtil;
import com.example.wemedia.service.CommentManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment/manage")
public class CommentManageController {

    @Autowired
    private CommentManageService commentManageService;

    @DeleteMapping("/del_comment/{commentId}")
    public ResponseResult delComment(@PathVariable("commentId") String commentId){
        return commentManageService.delComment(commentId);
    }

    @DeleteMapping("/del_comment_repay/{commentRepayId}")
    public ResponseResult delCommentRepay(@PathVariable("commentRepayId") String commentRepayId){
        return commentManageService.delCommentRepay(commentRepayId);
    }

    @PostMapping("/comment_repay")
    public ResponseResult saveCommentRepay(@RequestBody CommentRepaySaveDto dto){
        return commentManageService.saveCommentRepay(dto);
    }

    @PostMapping("/update_comment_status")
    public ResponseResult updateCommentStatus(@RequestBody CommentConfigDto dto){
        return commentManageService.updateCommentStatus(dto);
    }

    @PostMapping("/find_news_comments")
    public PageResponseResult findNewsComments(@RequestBody ArticleCommentDto dto){
        return commentManageService.findNewsComments(dto);
    }

    @PostMapping("/list")
    public ResponseResult list(@RequestBody CommentManageDto dto){
        return commentManageService.list(dto);
    }

    @PostMapping("/like")
    public ResponseResult like(@RequestBody CommentLikeDto dto){
        return commentManageService.like(dto);
    }

}