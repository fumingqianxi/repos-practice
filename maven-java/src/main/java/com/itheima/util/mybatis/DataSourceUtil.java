package com.itheima.util.mybatis;

import com.itheima.mybatis.custom.cfg.ConfigurationCustom;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 创建数据源工具类.
 *
 * @author 胡磊
 * @since 2023/9/25 15:26
 */
public class DataSourceUtil {

  /**
   * 获取连接.
   *
   * @param cfg 配置
   * @return 数据库连接
   */
  public static Connection getConnection(ConfigurationCustom cfg) {
    try {
      Class.forName(cfg.getDriver());
      return DriverManager.getConnection(cfg.getUrl(), cfg.getUsername(), cfg.getPassword());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
