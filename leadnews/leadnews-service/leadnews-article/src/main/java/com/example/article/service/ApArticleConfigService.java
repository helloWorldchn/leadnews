package com.example.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.article.pojo.ApArticleConfig;
import com.example.model.comment.dto.CommentConfigDto;
import com.example.model.common.dto.ResponseResult;

import java.util.Map;

public interface ApArticleConfigService extends IService<ApArticleConfig> {

    /**
     * 修改文章配置
     * @param map
     */
    public void updateByMap(Map map);

    /**
     * 修改文章评论状态
     * @return
     */
    public ResponseResult updateCommentStatus(CommentConfigDto dto);
}
