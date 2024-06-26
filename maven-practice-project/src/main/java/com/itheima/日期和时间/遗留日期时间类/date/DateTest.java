package com.itheima.日期和时间.遗留日期时间类.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
   java.util.DateDemo:表示日期和时间的类
   类 DateDemo 表示特定的瞬间，精确到毫秒。
   毫秒:千分之一秒 1000毫秒=1秒
   特定的瞬间:一个时间点,一刹那时间
   2088-08-08 09:55:33:333 瞬间
   2088-08-08 09:55:33:334 瞬间
   2088-08-08 09:55:33:334 瞬间
   ...
   毫秒值的作用:可以对时间和日期进行计算
   2099-01-03 到 2088-01-01 中间一共有多少天
   可以日期转换为毫秒进行计算,计算完毕,在把毫秒转换为日期

   把日期转换为毫秒:
       当前的日期:2088-01-01
       时间原点(0毫秒):1970 年 1 月 1 日 00:00:00(英国格林威治)
       就是计算当前日期到时间原点之间一共经历了多少毫秒 (3742767540068L)
   注意:
       中国属于东八区,会把时间增加8个小时
       1970 年 1 月 1 日 08:00:00

   把毫秒转换为日期:
       1 天 = 24 × 60 × 60 = 86400 秒  = 86400 x 1000 = 86400000毫秒
*/
public class DateTest {
  public static void main(String[] args) {
    demo01();
//    demo02();
//    demo03();
    demo04();
//    demo05();
  }

  /*
     Date类的空参数构造方法
     DateDemo() 获取当前系统的日期和时间
  */
  private static void demo01() {
//    Date date = new Date();
//    System.out.println(date);

    Date date = new Date(2019 - 1900, 11, 31, 11, 12, 13);
    System.out.println(date);
  }

  private static void demo02() {
    Date date = new Date(0);
    System.out.println(date);
  }

  private static void demo03() {
    Date date = new Date();
    System.out.println(date.getTime());
  }

  private static void demo04() {
    // 获取当前时间:
    Date date = new Date();
    System.out.println(date.getYear() + 1900); // 必须加上1900
    System.out.println(date.getMonth() + 1); // 0~11，必须加上1
    System.out.println(date.getDate()); // 1~31，不能加1
    System.out.println(date.getHours());
    System.out.println(date.getMinutes());
    System.out.println(date.getSeconds());
    // 转换为String:
    System.out.println(date.toString());
    // 转换为GMT时区:
    System.out.println(date.toGMTString());
    // 转换为本地时区:
    System.out.println(date.toLocaleString());
  }

  private static void demo05() {
    // 获取当前时间:
    Date date = new Date();
    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    System.out.println(sdf.format(date));

    sdf = new SimpleDateFormat("E MMM dd, yyyy");
    System.out.println(sdf.format(date));
  }
}
