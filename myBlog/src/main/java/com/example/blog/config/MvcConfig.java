package com.example.blog.config;
import com.example.blog.utils.LoginInterceptor;
import com.example.blog.utils.RefreshTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public void addInterceptors(InterceptorRegistry registry) { //添加拦截器
        //添加登录拦截器
        registry.addInterceptor(new LoginInterceptor(stringRedisTemplate))  //添加拦截器
                        .excludePathPatterns(   //需要排除的路径（不被拦截器拦截）
                                "/api/user/login",
                                "/api/user/email/code",
                                "/api/about",
                                "/api/articles/latest",   // 允许访问最新文章
                                "/api/articles",          // 允许访问文章列表
                                "/api/articles/**",       // 允许访问所有文章相关接口
                                "/api/categories"         // 允许访问分类
                        ).order(1);
        //添加刷新拦截器 token
        registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate))
                .addPathPatterns("/**").order(0);  //表示拦截所有
    }
}

/**
 * 这段代码的说明：
 * 这是一个Spring MVC的配置类，用于配置Web相关的功能，特别是拦截器的配置。
 * 配置了两个拦截器：
 * LoginInterceptor：登录拦截器，用于检查用户是否已登录
 * RefreshTokenInterceptor：Token刷新拦截器，用于刷新用户的登录状态
 * 拦截器的优先级配置：
 * Token刷新拦截器优先级为0
 * 登录拦截器优先级为1
 * 数字越小，优先级越高，所以Token刷新拦截器会先执行
 * 特别说明：
 * 登录接口和邮箱验证码接口不需要登录即可访问
 * Token刷新拦截器会拦截所有的请求（/**）
 * 使用StringRedisTemplate来操作Redis，用于处理用户登录状态的存储
 * 这种配置方式可以有效地管理用户的登录状态，并且能够自动刷新用户的token，提高系统的安全性和用户体验。
 */
