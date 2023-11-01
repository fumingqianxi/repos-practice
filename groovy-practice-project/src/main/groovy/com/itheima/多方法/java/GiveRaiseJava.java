package com.itheima.多方法.java;

import java.math.BigDecimal;

/**
 * @author 胡磊
 * @since 2022/12/4 11:53
 */
public class GiveRaiseJava {
  public static void giveRaise(Employee employee) {
    employee.raise(new BigDecimal(10000.00));
  }

  public static void main(String[] args) {
    giveRaise(new Employee());
    giveRaise(new Executive());
  }
}
