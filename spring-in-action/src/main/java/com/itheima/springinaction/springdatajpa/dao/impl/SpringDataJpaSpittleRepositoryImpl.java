package com.itheima.springinaction.springdatajpa.dao.impl;

import com.itheima.springinaction.springdatajpa.dao.SpringDataJpaSpittleRepositoryCustom;
import com.itheima.springinaction.springdatajpa.domain.SpringDataJpaSpittle;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author 胡磊
 * @since 2023/5/9 9:51
 */
public class SpringDataJpaSpittleRepositoryImpl implements SpringDataJpaSpittleRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<SpringDataJpaSpittle> findRecent() {
    return findRecent(10);
  }

  @Override
  public List<SpringDataJpaSpittle> findRecent(int count) {
    return (List<SpringDataJpaSpittle>) entityManager.createQuery("select s from SpringDataJpaSpittle s order by s.postedTime desc")
        .setMaxResults(count)
        .getResultList();
  }
}
