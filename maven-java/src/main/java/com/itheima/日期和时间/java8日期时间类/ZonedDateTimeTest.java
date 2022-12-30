package com.itheima.日期和时间.java8日期时间类;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author 胡磊
 * @since 2022/12/30 10:14
 */
public class ZonedDateTimeTest {
  public static void main(String[] args) {
    demo01();
  }

  public static void demo01() {
    ZonedDateTime zbj = ZonedDateTime.now(); // 默认时区
    ZonedDateTime zny = ZonedDateTime.now(ZoneId.of("America/New_York")); // 用指定时区获取当前时间
    System.out.println(zbj);
    System.out.println(zny);
  }
}
