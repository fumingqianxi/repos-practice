package com.itheima.Java业务开发常见错误100例.a12异常处理.handleexception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class APIResponse<T> {
    private boolean success;
    private T data;
    private int code;
    private String message;
}
