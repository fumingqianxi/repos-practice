package com.itheima.spring实战.spittr.aspect;

import com.itheima.spring实战.spittr.exception.DuplicateSpittleException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppWideExceptionHandler {

  @ExceptionHandler(DuplicateSpittleException.class)
  public String handleNotFound() {
    return "error/duplicate";
  }

}
