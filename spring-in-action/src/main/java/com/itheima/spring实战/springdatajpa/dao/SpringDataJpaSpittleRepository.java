package com.itheima.spring实战.springdatajpa.dao;

import com.itheima.spring实战.springdatajpa.domain.SpringDataJpaSpitter;
import com.itheima.spring实战.springdatajpa.domain.SpringDataJpaSpittle;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaSpittleRepository
    extends JpaRepository<SpringDataJpaSpittle, Long>, SpringDataJpaSpittleRepositoryCustom {

  List<SpringDataJpaSpittle> findBySpitterId(long spitterId);

  List<SpringDataJpaSpittle> findBySpitter(SpringDataJpaSpitter spitter);
}
