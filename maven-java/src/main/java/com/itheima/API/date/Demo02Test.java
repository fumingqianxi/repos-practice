package com.itheima.API.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/*
    练习:
        请使用日期时间相关的API，计算出一个人已经出生了多少天。
    分析:
        1.使用Scanner类中的方法next,获取出生日期
        2.使用DateFormat类中的方法parse,把字符串的出生日期,解析为Date格式的出生日期
        3.把Date格式的出生日期转换为毫秒值
        4.获取当前的日期,转换为毫秒值
        5.使用当前日期的毫秒值-出生日期的毫秒值
        6.把毫秒差值转换为天(s/1000/60/60/24)
 */
public class Demo02Test {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您的出生日期,格式:yyyy-MM-dd");
        String birthdayDateStr = sc.next();
        Date birthdayDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthdayDateStr);
        long birthdayTime = birthdayDate.getTime();
        long todayTime = new Date().getTime();
        long time = todayTime - birthdayTime;
        System.out.println(time / 1000 / 60 / 60 / 24);
    }
}
