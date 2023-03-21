package com.itheima;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DemoTest {
  public static void main(String[] args) throws ParseException {
    method03();
  }

  public static void method01() {
    String a = null;
    System.out.println(a.length());
  }

  /**
   * 替换字符串中转义字符.
   */
  public static void method02() {
    String a = "\\p";
    System.out.println(a.replaceAll("\\\\", "\\\\\\\\"));
  }

  /**
   * 测试LocalDateTime和ZonedDateTime关于时区的问题
   */
  public static void method03() {
    LocalDateTime localDateTime = LocalDateTime.now();
    System.out.println(localDateTime);
    ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
    System.out.println(zonedDateTime);
  }
}
