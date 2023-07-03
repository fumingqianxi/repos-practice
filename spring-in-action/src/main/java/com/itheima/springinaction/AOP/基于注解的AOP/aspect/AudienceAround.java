package com.itheima.springinaction.AOP.基于注解的AOP.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author 胡磊
 * @since 2023/4/27 18:49
 */
@Aspect
public class AudienceAround {

  @Pointcut("execution(* com.itheima.springinaction.AOP.model.Performance.perform(..))")
  public void performance() {}

  @Around("performance()")
  public void watchPerformance(ProceedingJoinPoint jp) {
    try {
      System.out.println("测试环绕通知");
      System.out.println("手机调静音");
      System.out.println("就坐");
      jp.proceed();
      System.out.println("鼓掌！");
    } catch (Throwable e) {
      System.out.println("退钱！");
    }
  }
}
