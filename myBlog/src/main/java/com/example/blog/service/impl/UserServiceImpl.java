package com.example.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.DTO.LoginFormDTO;
import com.example.blog.DTO.UserDTO;
import com.example.blog.common.Result;
import com.example.blog.entity.User;
import com.example.blog.mapper.UserMapper;
import com.example.blog.service.UserService;
import com.example.blog.utils.RegexUtils;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.example.blog.utils.RedisConstants.*;
import static com.example.blog.utils.SystemConstants.USER_NICK_NAME_PREFIX;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserMapper userMapper;


    @Override
    public Result sendCode(String email, HttpSession session) {
        //1、判断邮箱格式
        if(RegexUtils.isEmailInvalid(email)){
            //2、如果格式错误，返回错误信息
            return Result.error("邮箱格式错误");
        }

        //3、符合，生成验证码
        String code=RandomUtil.randomNumbers(6);
        //4、保存验证码到redis
        stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY+email,code,2,TimeUnit.MINUTES);
        //5、发送验证码，由于需要调用服务商的服务，此处用日志模拟
        log.debug("发送邮箱验证码成功，验证码：{}",code);
        //返回ok
        return Result.success();
    }

    @Override
    public Result login(LoginFormDTO loginFormDTO, HttpSession session) {
        //1、验证邮箱格式是否正确
        String email=loginFormDTO.getEmail();
    if(RegexUtils.isEmailInvalid(email)){
        //2、如果邮箱格式不正确
        return Result.error("邮箱格式错误");
    }

    System.out.println("执行到了这里************************");

    //3、从redis获取验证码并校验
        String cacheCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + email);
        String code=loginFormDTO.getCode();
        System.out.println("loginDto="+loginFormDTO+"*****************************");
        if(cacheCode==null||!cacheCode.equals(code)){
            //4、验证码不一致，报错
            return Result.error("验证码错误");
        }

        //5、验证码一致，根据邮箱查询用户
        User user = query().eq("email", email).one();
        System.out.println("user="+user+"********************************");

        //6、判断该用户是否存在
        if(user==null){
            //7、不存在，创建新用户并保存
            user=createUserWithEmail(email);
        }

        //8、保存用户信息到redis中
        //7.1随机生成额token,作为登录令牌
        String token= UUID.randomUUID().toString(true);

        //7.2讲User对象作为HashMap存储
        UserDTO userDTO= BeanUtil.copyProperties(user,UserDTO.class);
        Map<String,Object> userMap= BeanUtil.beanToMap(userDTO,new HashMap<>(), 
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName,fieldValue)->fieldValue.toString()));
        //7.3存储
        stringRedisTemplate.opsForHash().putAll(LOGIN_USER_KEY+token,userMap);
        //7.4、 设置token有效期  30分钟
        stringRedisTemplate.expire(LOGIN_USER_KEY+token,LOGIN_USER_TTL, TimeUnit.MINUTES);
        return Result.success(token);
    }


    private User createUserWithEmail(String email) {
        System.out.println(" createUserWithEmail()执行了*********************************");
        //1、创建用户
        User user=new User();
        user.setEmail(email).setNickName(USER_NICK_NAME_PREFIX+ RandomUtil.randomString(10));
        //2、保存用户
        save(user);
        return user;
    }



}
