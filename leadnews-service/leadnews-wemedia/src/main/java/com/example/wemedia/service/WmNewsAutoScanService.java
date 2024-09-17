package com.example.wemedia.service;

import com.example.model.common.dto.ResponseResult;
import com.example.model.wemedia.pojo.WmNews;

public interface WmNewsAutoScanService {
    /**
     * 自媒体文章审核
     * @param id  自媒体文章id
     */
    public void autoScanWmNews(Integer id);

    /**
     * 保存app文章数据
     * @param wmNews
     * @return
     */
    public ResponseResult saveAppArticle(WmNews wmNews);
}
