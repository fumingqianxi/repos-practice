package com.itheima.spring实战.AOP.基于注解的AOP.通过注解AOP添加方法;

/**
 * @author 胡磊
 * @since 2023/4/28 14:15
 */
public class DefaultEncoreable implements Encoreable {
  @Override
  public void performEncore() {
    System.out.println("表演中植入广告");
  }
}
