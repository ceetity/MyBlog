package com.example.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ${ceetity}
 * @github https://github.com/ceetity/works.git
 * @date 2024.11.30
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
