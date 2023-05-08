package com.itheima.spring实战.jdbc.dao;

import com.itheima.spring实战.jdbc.domain.JdbcSpitter;
import java.util.List;

public interface JdbcTemplateSpitterRepository {

  long count();
  
  void save(JdbcSpitter jdbcSpitter);
  
  JdbcSpitter findOne(long id);

  JdbcSpitter findByUsername(String username);

  List<JdbcSpitter> findAll();

}
