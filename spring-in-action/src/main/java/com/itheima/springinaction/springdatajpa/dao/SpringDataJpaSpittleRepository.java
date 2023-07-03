package com.itheima.springinaction.springdatajpa.dao;

import com.itheima.springinaction.springdatajpa.domain.SpringDataJpaSpitter;
import com.itheima.springinaction.springdatajpa.domain.SpringDataJpaSpittle;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaSpittleRepository
    extends JpaRepository<SpringDataJpaSpittle, Long>, SpringDataJpaSpittleRepositoryCustom {

  List<SpringDataJpaSpittle> findBySpitterId(long spitterId);

  List<SpringDataJpaSpittle> findBySpitter(SpringDataJpaSpitter spitter);
}
