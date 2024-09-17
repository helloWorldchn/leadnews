package com.example.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.article.dto.ArticleCommentDto;
import com.example.model.article.dto.ArticleHomeDto;
import com.example.model.article.pojo.ApArticle;
import com.example.model.article.vo.ArticleCommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface ApArticleMapper extends BaseMapper<ApArticle> {
    /**
     * 加载文章列表
     * @param dto
     * @param type  1  加载更多   2记载最新
     * @return
     */
    public List<ApArticle> loadArticleList(@Param("dto") ArticleHomeDto dto, @Param("type") Short type);

    public List<ApArticle> findArticleListByLast5days(@Param("dayParam") Date dayParam);

    List<ArticleCommentVo> findNewsComments(@Param("dto") ArticleCommentDto dto);

    int findNewsCommentsCount(@Param("dto")  ArticleCommentDto dto);

    public Map queryLikesAndCollections(@Param("wmUserId") Integer wmUserId, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);
}