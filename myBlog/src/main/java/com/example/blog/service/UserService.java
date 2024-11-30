package com.example.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.DTO.LoginFormDTO;
import com.example.blog.common.Result;
import com.example.blog.entity.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
/**
 * @author ${ceetity}
 * @github https://github.com/ceetity/works.git
 * @date 2024.11.30
 */
public interface UserService extends  IService<User> {

    Result login(LoginFormDTO loginFormDTO, HttpSession session);

   Result sendCode(String email, HttpSession session);
}
