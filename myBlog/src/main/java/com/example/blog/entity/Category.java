package com.example.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 文章分类实体类
 * 对应数据库中的category表
 */
@Data
@TableName("category")
public class Category {
    /**
     * 分类ID，主键，自动递增
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 分类名称
     */
    private String name;
    
    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;
}