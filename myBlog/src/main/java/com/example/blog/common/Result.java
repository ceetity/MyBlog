package com.example.blog.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 统一响应结果类
 * 用于封装所有API的返回结果
 * @param <T> 响应数据的类型
 */
@Data
@AllArgsConstructor
public class Result<T> {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 成功响应
     * @param data 响应数据
     * @return Result对象
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }

    public static <T> Result<T> success() {
        return new Result<>(200, "success",null);
    }

    /**
     * 错误响应
     * @param message 错误信息
     * @return Result对象
     */
    public static <T> Result<T> error(String message) {
        return new Result<>(500, message, null);
    }
}