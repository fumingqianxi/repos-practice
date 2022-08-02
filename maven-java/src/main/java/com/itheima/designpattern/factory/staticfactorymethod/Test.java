package com.itheima.designpattern.factory.staticfactorymethod;

import java.time.LocalDate;

/**
 * @author {胡磊}
 * @since 2022/6/3 23:08
 */
public class Test {

  public static void main(String[] args) {
    LocalDate ld = LocalDateFactory.fromInt(20200202);
    System.out.println(ld);
    LocalDate ld2 = LocalDateFactory.fromInt(20200202);
    System.out.println(ld == ld2);
  }
}
