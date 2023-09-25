package com.itheima.mybatis.custom.sqlsession.defaults;

import com.itheima.mybatis.custom.cfg.ConfigurationCustom;
import com.itheima.mybatis.custom.sqlsession.SqlSessionCustom;
import com.itheima.mybatis.custom.sqlsession.SqlSessionFactoryCustom;

/**
 * 自定义SqlSessionFactory实现类.
 *
 * @author 胡磊
 * @since 2023/9/25 13:48
 */
public class DefaultSqlSessionFactoryCustom implements SqlSessionFactoryCustom {

  private ConfigurationCustom cfg;

  public DefaultSqlSessionFactoryCustom(ConfigurationCustom cfg) {
    this.cfg = cfg;
  }

  @Override
  public SqlSessionCustom openSession() {
    return new DefaultSqlSessionCustom(cfg);
  }
}
