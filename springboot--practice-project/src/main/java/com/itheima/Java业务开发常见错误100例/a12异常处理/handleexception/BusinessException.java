package com.itheima.Java业务开发常见错误100例.a12异常处理.handleexception;

public class BusinessException extends RuntimeException {

    private int code;

    public BusinessException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
