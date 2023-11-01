package com.itheima.多方法.java;

/**
 * @author 胡磊
 * @since 2022/12/4 10:21
 */
public class Executive extends Employee {
  public void raise(Number amount) {
    System.out.println("Executive got raise");
  }

  public void raise(java.math.BigDecimal amount) {
    System.out.println("Executive got outlandish raise");
  }
}
