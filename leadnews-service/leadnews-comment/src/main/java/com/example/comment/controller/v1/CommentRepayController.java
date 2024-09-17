package com.example.comment.controller.v1;


import com.example.comment.service.CommentRepayService;
import com.example.model.comment.dto.CommentRepayDto;
import com.example.model.comment.dto.CommentRepayLikeDto;
import com.example.model.comment.dto.CommentRepaySaveDto;
import com.example.model.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comment_repay")
public class CommentRepayController{

    @Autowired
    private CommentRepayService commentRepayService;

    @PostMapping("/load")
    public ResponseResult loadCommentRepay(@RequestBody CommentRepayDto dto){
        return commentRepayService.loadCommentRepay(dto);
    }

    @PostMapping("/save")
    public ResponseResult saveCommentRepay(@RequestBody CommentRepaySaveDto dto){
        return commentRepayService.saveCommentRepay(dto);
    }

    @PostMapping("/like")
    public ResponseResult saveCommentRepayLike(@RequestBody CommentRepayLikeDto dto){
        return commentRepayService.saveCommentRepayLike(dto);
    }

}