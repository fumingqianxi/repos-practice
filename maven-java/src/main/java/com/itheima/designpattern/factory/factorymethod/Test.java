package com.itheima.designpattern.factory.factorymethod;

/**
 * @author {胡磊}
 * @since 2022/6/3 23:54
 */
public class Test {

  public static void main(String[] args) {
    LocalDateFactoryImpl factory = LocalDateFactory.getFactory();
    System.out.println(factory.fromInt(20220222));
  }
}
