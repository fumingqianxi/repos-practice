package com.itheima.spring实战.jpa.dao.impl;

import com.itheima.spring实战.jpa.dao.JpaSpitterRepository;
import com.itheima.spring实战.jpa.domain.JpaSpitter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 * @author 胡磊
 * @since 2023/5/8 13:38
 */
@Repository
public class JpaSpitterRepositoryImpl implements JpaSpitterRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public long count() {
    return findAll().size();
  }

  @Override
  public JpaSpitter save(JpaSpitter jpaSpitter) {
    entityManager.persist(jpaSpitter);
    return jpaSpitter;
  }

  @Override
  public JpaSpitter findOne(long id) {
    return entityManager.find(JpaSpitter.class, id);
  }

  @Override
  public JpaSpitter findByUsername(String username) {
    return (JpaSpitter) entityManager.createQuery("select s from JpaSpitter s where s.username=?").setParameter(1, username).getSingleResult();
  }

  @Override
  public List<JpaSpitter> findAll() {
    return (List<JpaSpitter>) entityManager.createQuery("select s from JpaSpitter s").getResultList();
  }
}
