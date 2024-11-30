package com.example.blog.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginFormDTO {
    private String email;  //邮箱
    private String code;  //验证码
    private String password;  //密码
}
