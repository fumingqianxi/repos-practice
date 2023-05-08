package com.itheima.spring实战.spittr.db;

import com.itheima.spring实战.spittr.domain.Spittle;
import java.util.List;

public interface SpittleRepository {

  List<Spittle> findRecentSpittles();

  List<Spittle> findSpittles(long max, int count);
  
  Spittle findOne(long id);

  void save(Spittle spittle);

  long count();

  List<Spittle> findRecent();

  List<Spittle> findRecent(int count);

  List<Spittle> findBySpitterId(long spitterId);

  void delete(long id);

}
