package com.itheima.mybatis.custom.sqlsession;

/**
 * 自定义Mybatis中和数据库交互的核心类.
 *
 * @author 胡磊
 * @since 2023/9/25 11:22
 */
public interface SqlSessionCustom {

  /**
   * 根据参数创建一个代理对象.
   *
   * @param daoInterfaceClass 接口类字节码
   * @return 代理类
   * @param <T> 泛型
   */
  <T> T getMapper(Class<T> daoInterfaceClass);

  /**
   * 释放资源.
   */
  void close();
}
