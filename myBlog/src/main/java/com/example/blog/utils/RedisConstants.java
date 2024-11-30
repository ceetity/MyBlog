package com.example.blog.utils;
/**
 * @author ${ceetity}
 * @github https://github.com/ceetity/works.git
 * @date 2024.11.30
 */

//存放与redis有关的常量
public class RedisConstants {
    public static final String LOGIN_CODE_KEY="login:code:";
    public static final Long LOGIN_USER_TTL = 30L;
    public static final String LOGIN_USER_KEY = "login:token:";
}
