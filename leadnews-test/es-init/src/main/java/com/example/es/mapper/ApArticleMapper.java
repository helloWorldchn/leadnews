package com.example.es.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.es.pojo.SearchArticleVo;
import com.example.model.article.pojo.ApArticle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApArticleMapper extends BaseMapper<ApArticle> {

    public List<SearchArticleVo> loadArticleList();

}
