package com.itheima.springinaction.spittr.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 胡磊
 * @since 2023/5/23 11:00
 */
//@Aspect
@Component
@Order(0)
public class ExceptionAspect {

  @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
  public void requestMapping() {
    // 找到被RequestMapping注解过的方法
  }

  @Around("requestMapping()")
  public void exceptionAspect(ProceedingJoinPoint joinPoint) throws Throwable {
    try {
      joinPoint.proceed();
    } catch (Exception e) {
      System.out.println("ExceptionAspect拦截到异常" + e.getMessage());
    }
  }
}
