package com.itheima.springinaction.jdbc.dao;

import com.itheima.springinaction.jdbc.domain.JdbcSpitter;

public interface JdbcSpitterRepository {

  void addSpitter(JdbcSpitter spitter);

  // 不通过数据源注入实现
  void saveSpitter(JdbcSpitter spitter);

  void updateSpitter(JdbcSpitter spitter);

  JdbcSpitter findOne(long id);
}
