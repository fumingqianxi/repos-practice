package com.itheima.spring实战.jpa.dao;

import com.itheima.spring实战.jpa.domain.JpaSpittle;
import java.util.List;

/**
 * @author 胡磊
 * @since 2023/5/8 9:44
 */
public interface JpaSpittleRepository {

  long count();

  List<JpaSpittle> findRecent();

  List<JpaSpittle> findRecent(int count);

  JpaSpittle findOne(long id);

  JpaSpittle save(JpaSpittle jpaSpittle);

  List<JpaSpittle> findBySpitterId(long spitterId);

  void delete(long id);
}
