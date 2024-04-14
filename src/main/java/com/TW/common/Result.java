package com.TW.common;


import lombok.Data;

/**
 * 通用响应封装类
 * @param <T> 数据类型
 */
@Data
public class Result<T> {
    private boolean success; // 操作是否成功
    private Integer code;    // 返回码
    private String message;  // 返回信息
    private T data;          // 返回数据

    public Result() {
    }

    public Result(boolean success, Integer code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功时调用
    public static <T> Result<T> ok(T data) {
        return new Result<>(true,200, "操作成功", data);
    }

    // 失败时调用
    public static <T> Result<T> fail(String message) {
        return new Result<>(false,500, message, null);
    }

    // Getter和Setter省略
    // ...
}

