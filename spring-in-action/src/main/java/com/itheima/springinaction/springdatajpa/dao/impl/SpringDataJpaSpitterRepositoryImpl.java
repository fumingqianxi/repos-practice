package com.itheima.springinaction.springdatajpa.dao.impl;

import com.itheima.springinaction.springdatajpa.dao.SpringDataJpaSpitterSweeper;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author 胡磊
 * @since 2023/5/9 9:57
 */
public class SpringDataJpaSpitterRepositoryImpl implements SpringDataJpaSpitterSweeper {

  @PersistenceContext
  private EntityManager em;

  @Override
  public int eliteSweep() {
    String update =
        "UPDATE SpringDataJpaSpitter spitter " +
            "SET spitter.status = 'Elite' " +
            "WHERE spitter.status = 'Newbie' " +
            "AND spitter.id IN (" +
            "SELECT s FROM SpringDataJpaSpitter s WHERE (" +
            "  SELECT COUNT(spittles) FROM s.spittles spittles) > 10000" +
            ")";
    return em.createQuery(update).executeUpdate();
  }
}
