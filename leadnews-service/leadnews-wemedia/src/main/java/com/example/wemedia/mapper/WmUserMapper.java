package com.example.wemedia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.wemedia.pojo.WmUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WmUserMapper extends BaseMapper<WmUser> {
}