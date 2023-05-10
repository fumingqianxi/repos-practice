package com.itheima.spring实战.springdatajpa.dao;

import com.itheima.spring实战.springdatajpa.domain.SpringDataJpaSpittle;
import java.util.List;

public interface SpringDataJpaSpittleRepositoryCustom {

  List<SpringDataJpaSpittle> findRecent();

  List<SpringDataJpaSpittle> findRecent(int count);

}