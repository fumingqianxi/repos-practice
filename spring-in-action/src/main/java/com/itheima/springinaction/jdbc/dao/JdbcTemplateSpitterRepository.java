package com.itheima.springinaction.jdbc.dao;

import com.itheima.springinaction.jdbc.domain.JdbcSpitter;
import java.util.List;

public interface JdbcTemplateSpitterRepository {

  long count();

  void save(JdbcSpitter jdbcSpitter);

  JdbcSpitter findOne(long id);

  JdbcSpitter findByUsername(String username);

  List<JdbcSpitter> findAll();

}
