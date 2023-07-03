package com.itheima.springinaction.springdatajpa.dao;

import com.itheima.springinaction.springdatajpa.domain.SpringDataJpaSpittle;
import java.util.List;

public interface SpringDataJpaSpittleRepositoryCustom {

  List<SpringDataJpaSpittle> findRecent();

  List<SpringDataJpaSpittle> findRecent(int count);

}