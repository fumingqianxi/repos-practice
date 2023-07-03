package com.itheima.springinaction.spittr.aspect;

import com.itheima.springinaction.spittr.exception.DuplicateSpittleException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppWideExceptionHandler {

  @ExceptionHandler(DuplicateSpittleException.class)
  public void handleNotFound(Exception e) {
    System.out.println("AppWideExceptionHandler拦截到异常" + e.getMessage());
  }
}
