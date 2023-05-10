package com.itheima.spring实战.spittr.dao;

import com.itheima.spring实战.spittr.domain.Spittle;
import java.util.List;

public interface SpittleRepositoryCustom {

  List<Spittle> findRecentSpittles();
}