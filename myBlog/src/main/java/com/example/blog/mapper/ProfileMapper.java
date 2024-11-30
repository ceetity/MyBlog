package com.example.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.entity.Profile;
import org.apache.ibatis.annotations.Mapper;

/**
 * 个人信息数据访问层接口
 * 继承BaseMapper以获得基本的CRUD功能
 */
@Mapper
public interface ProfileMapper extends BaseMapper<Profile> {
}