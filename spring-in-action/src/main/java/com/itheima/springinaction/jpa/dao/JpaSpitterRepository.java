package com.itheima.springinaction.jpa.dao;

import com.itheima.springinaction.jpa.domain.JpaSpitter;
import java.util.List;

/**
 * @author 胡磊
 * @since 2023/5/8 9:40
 */
public interface JpaSpitterRepository {

  long count();

  JpaSpitter save(JpaSpitter jpaSpitter);

  JpaSpitter findOne(long id);

  JpaSpitter findByUsername(String username);

  List<JpaSpitter> findAll();
}
