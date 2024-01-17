package com.example.comment.pojo;

import lombok.Data;

import java.util.List;

@Data
public class CommentRepayListVo  {

    private ApComment apComments;
    private List<ApCommentRepay> apCommentRepays;
}