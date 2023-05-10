package com.itheima.spring实战.spittr.dao;

import com.itheima.spring实战.spittr.domain.Spittle;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpittleRepository extends JpaRepository<Spittle, Long>, SpittleRepositoryCustom {

  @Query(value = "select * from spittle where id < ?1 ORDER BY posted_time DESC LIMIT ?2", nativeQuery=true)
  List<Spittle> findSpittles(long max, int count);
}
