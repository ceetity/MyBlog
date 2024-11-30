package com.example.blog.controller;

import com.example.blog.DTO.LoginFormDTO;
import com.example.blog.common.Result;
import com.example.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/email/code")
    public Result sendcode(@RequestParam("email") String email,HttpSession session){
         //发送验证码并保存验证信息
      return   userService.sendCode(email,session);
    }


    @PostMapping("/login")
    public Result login(@RequestBody LoginFormDTO loginFormDTO,HttpSession session){
        //实现登录功能
        return userService.login(loginFormDTO,session);

    }




}
