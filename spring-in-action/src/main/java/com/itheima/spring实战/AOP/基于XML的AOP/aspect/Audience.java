package com.itheima.spring实战.AOP.基于XML的AOP.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author 胡磊
 * @since 2023/4/27 18:49
 */
public class Audience {

  public void silenceCellPhones() {
    System.out.println("手机调静音");
  }

  public void takeSeats() {
    System.out.println("就坐");
  }

  public void applause() {
    System.out.println("鼓掌！");
  }

  public void demandRefund() {
    System.out.println("要求退钱");
  }

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
