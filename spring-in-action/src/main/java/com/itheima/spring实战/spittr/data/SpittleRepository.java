package com.itheima.spring实战.spittr.data;

import com.itheima.spring实战.spittr.Spittle;
import java.util.List;

public interface SpittleRepository {

  List<Spittle> findRecentSpittles();

  List<Spittle> findSpittles(long max, int count);
  
  Spittle findOne(long id);

  void save(Spittle spittle);

}
