package com.example.wemedia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.wemedia.dto.NewsAuthDto;
import com.example.model.wemedia.pojo.WmNews;
import com.example.model.wemedia.vo.WmNewsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface WmNewsMapper  extends BaseMapper<WmNews> {
    List<WmNewsVo> findListAndPage(@Param("dto") NewsAuthDto dto);

    int findListCount(@Param("dto") NewsAuthDto dto);
}
