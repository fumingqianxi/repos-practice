package com.itheima.mybatis.custom.sqlsession.defaults;

import com.itheima.mybatis.custom.cfg.ConfigurationCustom;
import com.itheima.mybatis.custom.sqlsession.SqlSessionCustom;
import com.itheima.mybatis.custom.sqlsession.proxy.MapperProxyCustom;
import com.itheima.util.mybatis.DataSourceUtil;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 自定义SqlSession实现类.
 *
 * @author 胡磊
 * @since 2023/9/25 13:40
 */
public class DefaultSqlSessionCustom implements SqlSessionCustom {

  private ConfigurationCustom cfg;
  private Connection connection;

  public DefaultSqlSessionCustom(ConfigurationCustom cfg) {
    this.cfg = cfg;
    this.connection = DataSourceUtil.getConnection(cfg);
  }

  @Override
  public <T> T getMapper(Class<T> daoInterfaceClass) {
    return (T) Proxy.newProxyInstance(
        daoInterfaceClass.getClassLoader(), new Class[]{daoInterfaceClass},
        new MapperProxyCustom(cfg.getMappers(), connection));
  }

  @Override
  public void close() {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
