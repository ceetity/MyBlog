package com.example.blog.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.example.blog.DTO.UserDTO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/**
 * @author ${ceetity}
 * @github https://github.com/ceetity/works.git
 * @date 2024.11.30
 */
public class RefreshTokenInterceptor implements HandlerInterceptor {


    private StringRedisTemplate stringRedisTemplate ;

    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate){
           this.stringRedisTemplate=stringRedisTemplate;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1、获取请求头中的token
        String token = request.getHeader("authorization");

        if(StrUtil.isBlank(token)){
            return true;  //放行（ 无token，不是我的职责，交给LoginInterceptor处理）
        }


        //2、基于TOKEN来获取redis中的用户
        String key=RedisConstants.LOGIN_USER_KEY+token;
        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(RedisConstants.LOGIN_USER_KEY+token);
        //3、判断用户是否存在
        if(userMap.isEmpty()){
            // Redis 中找不到用户，说明 token 已失效
            response.setStatus(401);  // 设置 401 状态码
            return true;// 放行原因：
            // 1. 这不是RefreshTokenInterceptor的职责
            // 2. 让LoginInterceptor去处理未登录的情况
            // 3. 保持拦截器职责单一
        }

        //5、将查询到的Hash数据转为UserDTO对象
        UserDTO userDTO = BeanUtil.fillBeanWithMap(userMap, new UserDTO(), false);//第三个形参表是否忽略转换过程中的错误
        //6、存在，保存用户信息到ThreadLocal（即保存到当前线程）
        UserHolder.saveUser(userDTO);  //相当于 ThreadLocal<UserDTO> tl ,  tl.set(user);
        //7、刷新（续期）token有效期
        stringRedisTemplate.expire(key,RedisConstants.LOGIN_USER_TTL, TimeUnit.MINUTES);  //常量RedisConstants.LOGIN_USER_TTL表多少分钟
        //8、放行
        return true;
    }



    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
