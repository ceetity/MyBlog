package com.example.blog.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper  = false)  //callSuper = false表示生成的equals和hashCode方法不会包含父类的属性
@Accessors(chain = true)  //开启链式编程风格
@TableName("user")
public class User implements Serializable {  //表明该类的对象可以被序列化
    private static final long serialVersionUID = 1L; //用于类序列化的版本控制

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String email;

    private String password;

    private String nickName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
