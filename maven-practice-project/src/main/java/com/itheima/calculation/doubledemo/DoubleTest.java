package com.itheima.calculation.doubledemo;

import org.junit.Test;

/**
 * double测试.
 *
 * @author 胡磊
 * @since 2024/3/4 13:35
 */
public class DoubleTest {

  /**
   * 反直觉的四则运算结果，异常和正常情况作了对照，需要注意的是异常情况是少数，是精心设计的，
   * 正常情况是多数，可能随便改个异常情况的数据，结果就正常了，但是异常只要出现就是致命的，不能因为少就忽视.
   */
  @Test
  public void test01() {
    System.out.println("异常情况：");
    System.out.println(0.1 + 0.2);
    System.out.println(1.0 - 0.8);
    System.out.println(4.015 * 100);
    System.out.println(123.3 / 100);
    double amount1 = 2.15;
    double amount2 = 1.10;
    if (amount1 - amount2 == 1.05) {
      System.out.println("OK");
    }
    System.out.println("===============分隔线===============");
    System.out.println("正常情况：");
    System.out.println(0.1 + 0.3);
    System.out.println(1.0 - 0.4);
    System.out.println(4.015 * 10);
    System.out.println(123.3 / 10);
    double amount3 = 2.15;
    double amount4 = 1.2;
    if (amount3 - amount4 == 0.95) {
      System.out.println("OK");
    }
  }
}
