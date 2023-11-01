package com.itheima.mybatis.custom.sqlsession.proxy;

import com.itheima.mybatis.custom.cfg.MapperCustom;
import com.itheima.util.mybatis.ExecutorUtil;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

/**
 * 代理类.
 *
 * @author 胡磊
 * @since 2023/9/25 13:25
 */
public class MapperProxyCustom implements InvocationHandler {

  // map的key是全限定类名+方法名
  private Map<String, MapperCustom> mappers;
  private Connection conn;

  public MapperProxyCustom(Map<String, MapperCustom> mappers, Connection conn) {
    this.mappers = mappers;
    this.conn = conn;
  }


  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    // 获取方法名
    String methodName = method.getName();
    // 获取类名
    String className = method.getDeclaringClass().getName();
    String key = className + "." + methodName;
    MapperCustom mapper = mappers.get(key);
    if (mapper == null) {
      throw new IllegalArgumentException("传入的参数有误");
    }
    return ExecutorUtil.selectList(mapper, conn);
  }
}
