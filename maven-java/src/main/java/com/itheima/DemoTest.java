package com.itheima;

import com.google.common.base.CaseFormat;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DemoTest {
  public static void main(String[] args) throws ParseException {
//    method04("group2/M00/09/07/oYYBAGHdE2-ABP27AADAAOv5PNo673.xls");
//    method05();
//    method06();
    method07("ArrayIndexOutOfBoundsException");
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

  /**
   * 测试删除掉url部分前缀
   */
  public static void method04(String url) {
    if (url.matches("group\\d/M00/.*")) {
      url = url.replaceFirst("group\\d/M00/", "");
    }
    System.out.println(url);
  }

  /**
   * 测试两个日期的小时差.
   */
  public static void method05() {
    Date date1 = new Date();
    Date date2 = new Date(date1.getTime() + 9100000000L);
    // 必须用1000.0，否则结果不会保留小数
    double diff = (date2.getTime() - date1.getTime()) / 1000.0 / 60 / 60;
    NumberFormat numberInstance = NumberFormat.getNumberInstance();
    numberInstance.setMaximumFractionDigits(2);
    numberInstance.setRoundingMode(RoundingMode.HALF_UP);
    numberInstance.setGroupingUsed(false);
    diff = Double.parseDouble(numberInstance.format(diff));
    System.out.println(diff);
  }

  /**
   * 测试获取异常类名称.
   */
  public static void method06() {
    try{
      int i = 1 / 0;
    } catch (Exception e) {
      System.out.println(e.getClass().getSimpleName());
    }
  }

  /**
   * 测试将驼峰字符串转为大写下划线.
   *
   * @param str 原字符串
   */
  public static void method07(String str) {
    System.out.println(CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, str));
  }
}
