package com.itheima.spring实战.spittr.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 胡磊
 * @since 2023/5/23 11:00
 */
@Aspect
@Component
@Order(-1)
public class RestControllerAspect {

  @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
  public void requestMapping() {
    // 找到被RequestMapping注解过的方法
  }

  @Pointcut("execution(public * org.springframework..*.*(..))")
  public void springframeworkController() {
    // spring 自有的controller资源
  }

  @Around("requestMapping() && (!springframeworkController())")
  public Object restControllerAspect(ProceedingJoinPoint joinPoint) throws Throwable {
    try {
      System.out.println("打印开始日志");
      Signature signature = joinPoint.getSignature();
      MethodSignature methodSignature = (MethodSignature) signature;
      // get class name
      String className = joinPoint.getTarget().getClass().getName();
      // get method name
      String methodName = signature.getName();
      // get params
      Object[] args = joinPoint.getArgs();
      String[] argNames = methodSignature.getParameterNames();
      System.out.println("拦截到的方法为：" + className + "." + methodName);
      Object proceedResult = joinPoint.proceed();
      System.out.println(proceedResult);
      System.out.println("打印结束日志");
      return proceedResult;
    } catch (Exception e) {
      System.out.println("打印异常日志");
      System.out.println("RestControllerAspect拦截到异常" + e.getMessage());
      return "error/duplicate";
    }
  }
}
