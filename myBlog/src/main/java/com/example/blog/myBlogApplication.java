package com.example.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author ${ceetity}
 * @github https://github.com/ceetity/works.git
 * @date 2024.11.30
 */
@SpringBootApplication
@MapperScan("com.example.blog.mapper")
public class myBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(myBlogApplication.class, args);
    }

}
