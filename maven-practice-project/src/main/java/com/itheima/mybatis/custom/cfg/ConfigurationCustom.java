package com.itheima.mybatis.custom.cfg;

import java.util.HashMap;
import java.util.Map;

/**
 * mybatis的配置类.
 *
 * @author 胡磊
 * @since 2023/9/25 11:04
 */
public class ConfigurationCustom {

  private String driver;
  private String url;
  private String username;
  private String password;
  private Map<String, MapperCustom> mappers = new HashMap<>();

  public String getDriver() {
    return driver;
  }

  public void setDriver(String driver) {
    this.driver = driver;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Map<String, MapperCustom> getMappers() {
    return mappers;
  }

  public void setMappers(Map<String, MapperCustom> mappers) {
    // 此处需要使用追加的方式
    this.mappers.putAll(mappers);
  }
}
