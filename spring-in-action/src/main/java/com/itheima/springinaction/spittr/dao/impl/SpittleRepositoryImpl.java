package com.itheima.springinaction.spittr.dao.impl;

import com.itheima.springinaction.spittr.dao.SpittleRepositoryCustom;
import com.itheima.springinaction.spittr.domain.Spittle;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author 胡磊
 * @since 2023/5/9 9:51
 */
public class SpittleRepositoryImpl implements SpittleRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Spittle> findRecentSpittles() {
    return findRecent(10);
  }

  public List<Spittle> findRecent(int count) {
    return (List<Spittle>) entityManager.createQuery("select s from Spittle s order by s.time desc")
        .setMaxResults(count)
        .getResultList();
  }
}
