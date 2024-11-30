package com.example.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 文章实体类
 * 对应数据库中的article表
 */
@Data  // Lombok注解，自动生成getter、setter、toString等方法
@TableName("article")  // 指定对应的数据库表名
public class Article {
    /**
     * 文章ID，主键，自动递增
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章描述/摘要
     */
    private String description;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章分类ID，关联category表
     */
    private Long categoryId;

    /**
     * 文章浏览量
     */
    private Integer views;
    
    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;
}