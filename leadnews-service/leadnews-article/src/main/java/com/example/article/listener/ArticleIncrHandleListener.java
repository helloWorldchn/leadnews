package com.example.article.listener;

import com.alibaba.fastjson.JSON;
import com.example.article.service.ApArticleService;
import com.example.common.constants.HotArticleConstants;
import com.example.model.message.ArticleVisitStreamMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ArticleIncrHandleListener {

    @Autowired
    private ApArticleService apArticleService;

    @KafkaListener(topics = HotArticleConstants.HOT_ARTICLE_INCR_HANDLE_TOPIC)
    public void onMessage(String mess){
        if(StringUtils.isNotBlank(mess)){
            //System.out.println(mess);
            ArticleVisitStreamMessage articleVisitStreamMessage = JSON.parseObject(mess, ArticleVisitStreamMessage.class);
            apArticleService.updateScore(articleVisitStreamMessage);
        }
    }
}