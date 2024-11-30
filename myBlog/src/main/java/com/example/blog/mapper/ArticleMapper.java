package com.example.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 文章数据访问层接口
 * 继承BaseMapper以获得基本的CRUD功能
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    @Select("SELECT * FROM article ORDER BY create_time DESC LIMIT #{limit}")
    List<Article> selectLatestArticles(int limit);


    @Select("SELECT * FROM article  WHERE category_id = #{categoryId}")
    List<Article> selectByCategoryId(Long categoryId);
}