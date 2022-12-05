package com.itheima.字符串.字面常量与表达式.java;

/**
 * @author 胡磊
 * @since 2022/12/5 10:16
 */
public class Expressions {
  public static void main(String[] args) {
    StringBuilder sb = new StringBuilder("fence");
    String text = "The cow jumped over the " + sb;
    System.out.println(text);
    sb.replace(0, 5, "moon");
    System.out.println(text);
  }
}
