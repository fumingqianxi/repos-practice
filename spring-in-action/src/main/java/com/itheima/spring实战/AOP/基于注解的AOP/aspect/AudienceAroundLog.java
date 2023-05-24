package com.itheima.spring实战.AOP.基于注解的AOP.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

/**
 * @author 胡磊
 * @since 2023/4/27 18:49
 */
@Aspect
@Order(-1)
public class AudienceAroundLog {

  @Pointcut("execution(* com.itheima.spring实战.AOP.model.Performance.perform(..))")
  public void performance() {}

  @Around("performance()")
  public void watchPerformance(ProceedingJoinPoint jp) {
    try {
      System.out.println("开始日志");
      jp.proceed();
      System.out.println("结束日志");
    } catch (Throwable e) {
      System.out.println("异常日志");
    }
  }
}
