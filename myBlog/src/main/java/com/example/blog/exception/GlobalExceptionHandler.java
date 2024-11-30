package com.example.blog.exception;

import com.example.blog.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * @author ${ceetity}
 * @github https://github.com/ceetity/works.git
 * @date 2024.11.30
 */


/**
 * 全局异常处理器
 * 统一处理系统中出现的异常
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有未捕获的异常
     * @param e 异常对象
     * @return 统一的错误响应
     */
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        // 记录日志
        e.printStackTrace();
        return Result.error(e.getMessage());
    }
}