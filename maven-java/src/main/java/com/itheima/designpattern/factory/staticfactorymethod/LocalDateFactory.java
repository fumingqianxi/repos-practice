package com.itheima.designpattern.factory.staticfactorymethod;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用静态工厂方法实现一个类似20200202的整数转换为LocalDate.
 *
 * @author {胡磊}
 * @since 2022/6/3 22:57
 */
public class LocalDateFactory {

  private static Map<Integer, LocalDate> cache = new HashMap<>();

  public static LocalDate fromInt(int yyyyMMdd) {
    if (yyyyMMdd >= 20190222 && yyyyMMdd <= 20300222) {
      LocalDate result = cache.get(yyyyMMdd);
      if (result == null) {
        result = create(yyyyMMdd);
        cache.put(yyyyMMdd, result);
      }
      return result;
    }
    return create(yyyyMMdd);
  }

  private static LocalDate create(int yyyyMMdd) {
    DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
    return LocalDate.parse(yyyyMMdd + "", formatter);
  }
}
