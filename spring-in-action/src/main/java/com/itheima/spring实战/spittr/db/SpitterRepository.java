package com.itheima.spring实战.spittr.db;

import com.itheima.spring实战.spittr.domain.Spitter;
import java.util.List;

public interface SpitterRepository {

  void addSpitter(Spitter spitter);

  long count();

  Spitter save(Spitter spitter);

  Spitter findOne(long id);

  Spitter findByUsername(String username);

  List<Spitter> findAll();

}
