package com.itheima.springinaction.jpa.dao.impl;

import com.itheima.springinaction.jpa.dao.JpaSpittleRepository;
import com.itheima.springinaction.jpa.domain.JpaSpittle;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 * @author 胡磊
 * @since 2023/5/8 13:38
 */
@Repository
public class JpaSpittleRepositoryImpl implements JpaSpittleRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public long count() {
    return findAll().size();
  }

  @Override
  public List<JpaSpittle> findRecent() {
    return findRecent(10);
  }

  @Override
  public List<JpaSpittle> findRecent(int count) {
    return (List<JpaSpittle>) entityManager.createQuery("select s from JpaSpittle s order by s.postedTime desc")
        .setMaxResults(count)
        .getResultList();
  }

  @Override
  public JpaSpittle findOne(long id) {
    return entityManager.find(JpaSpittle.class, id);
  }

  @Override
  public JpaSpittle save(JpaSpittle jpaSpittle) {
    entityManager.persist(jpaSpittle);
    return jpaSpittle;
  }

  @Override
  public List<JpaSpittle> findBySpitterId(long spitterId) {
    return (List<JpaSpittle>) entityManager.createQuery("select s from JpaSpittle s, JpaSpitter sp where s.spitter = sp and sp.id=? order by s.postedTime desc")
        .setParameter(1, spitterId)
        .getResultList();
  }

  @Override
  public void delete(long id) {
    entityManager.remove(findOne(id));
  }

  public List<JpaSpittle> findAll() {
    return (List<JpaSpittle>) entityManager.createQuery("select s from JpaSpittle s").getResultList();
  }
}
