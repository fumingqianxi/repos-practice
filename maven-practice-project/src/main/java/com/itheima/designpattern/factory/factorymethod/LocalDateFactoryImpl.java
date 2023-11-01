package com.itheima.designpattern.factory.factorymethod;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author 胡磊
 * @since 2022/6/3 23:42
 */
public class LocalDateFactoryImpl implements LocalDateFactory{

  @Override
  public LocalDate fromInt(Integer yyyyMMdd) {
    DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
    return LocalDate.parse(yyyyMMdd + "", formatter);
  }
}
