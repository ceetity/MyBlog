package com.example.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import java.util.List;

@Data
@TableName(value = "profile", autoResultMap = true)
public class Profile {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String title;
    private String avatar;
    private String email;
    private String github;

    @TableField(typeHandler = com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler.class)
    private List<String> skills;
    private String description;
}