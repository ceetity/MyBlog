package com.example.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置类
 * 允许前端应用访问后端API
 */
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")  // 允许跨域的路径
                        .allowedOrigins("http://localhost:8080")  // 允许跨域的源
                        .allowedMethods("GET", "POST", "PUT", "DELETE")  // 允许的方法
                        .allowedHeaders("*")  // 允许的请求头
                        .allowCredentials(true);  // 是否允许携带cookie
            }
        };
    }
}