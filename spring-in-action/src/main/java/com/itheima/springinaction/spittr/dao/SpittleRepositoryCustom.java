package com.itheima.springinaction.spittr.dao;

import com.itheima.springinaction.spittr.domain.Spittle;
import java.util.List;

public interface SpittleRepositoryCustom {

  List<Spittle> findRecentSpittles();
}