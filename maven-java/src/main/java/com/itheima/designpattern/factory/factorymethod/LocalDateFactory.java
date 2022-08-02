package com.itheima.designpattern.factory.factorymethod;

import java.time.LocalDate;

/**
 * @author {胡磊}
 * @since 2022/6/3 23:41
 */
public interface LocalDateFactory {

  LocalDate fromInt(Integer yyyyMMdd);

  static LocalDateFactoryImpl impl = new LocalDateFactoryImpl();

  static LocalDateFactoryImpl getFactory() {
    return impl;
  }
}
