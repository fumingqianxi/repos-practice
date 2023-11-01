package com.itheima.日期和时间.遗留日期时间类;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author 胡磊
 * @since 2022/12/30 9:03
 */
public class SimpleDateFormatTest {

  public static void main(String[] args) throws Exception {
    demo01();
//    demo02();
  }

  public static void demo01() throws Exception {
    /* 对于同一个时间表示，不同时区转换为Date会得到不同的时间戳 */
    String stringDate = "2020-01-02 22:00:00";
    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date1 = inputFormat.parse(stringDate);
    System.out.println(date1 + ":" + date1.getTime());
    inputFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
    Date date2 = inputFormat.parse(stringDate);
    System.out.println(date2 + ":" + date2.getTime());
  }

  public static void demo02() throws Exception {
    /* 同一个 Date，在不同的时区下格式化得到不同的时间表示 */
    String stringDate = "2020-01-02 22:00:00";
    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //同一Date
    Date date = inputFormat.parse(stringDate);
    //默认时区格式化输出：
    System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss Z]").format(date));
    //纽约时区格式化输出
    TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
    System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss Z]").format(date));
  }
}
