package com.itheima.日期和时间.java8日期时间类;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author 胡磊
 * @since 2022/12/30 9:50
 */
public class LocalDateTimeTest {
  public static void main(String[] args) {
    demo01();
  }

  public static void demo01() {
    /* 创建日期时间 */
    LocalDate d = LocalDate.now(); // 当前日期
    LocalTime t = LocalTime.now(); // 当前时间
    LocalDateTime dt = LocalDateTime.now(); // 当前日期和时间
    System.out.println(d); // 严格按照ISO 8601格式打印
    System.out.println(t); // 严格按照ISO 8601格式打印
    System.out.println(dt); // 严格按照ISO 8601格式打印
  }
}
