package com.itheima.spring实战.AOP.基于注解的AOP.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author 胡磊
 * @since 2023/4/27 18:49
 */
@Aspect
public class Audience {

  @Pointcut("execution(* com.itheima.spring实战.AOP.model.Performance.perform(..))")
  public void performance() {}

  @Before("performance()")
//  @Before("execution(* com.itheima.spring实战.AOP.model.Performance.perform(..))")
  public void silenceCellPhones() {
    System.out.println("手机调静音");
  }

//  @Before("execution(* com.itheima.spring实战.AOP.model.Performance.perform(..))")
  @Before("performance()")
  public void takeSeats() {
    System.out.println("就坐");
  }

//  @AfterReturning("execution(* com.itheima.spring实战.AOP.model.Performance.perform(..))")
  @AfterReturning("performance()")
  public void applause() {
    System.out.println("鼓掌！");
  }

//  @AfterThrowing("execution(* com.itheima.spring实战.AOP.model.Performance.perform(..))")
  @AfterThrowing("performance()")
  public void demandRefund() {
    System.out.println("要求退钱");
  }
}
