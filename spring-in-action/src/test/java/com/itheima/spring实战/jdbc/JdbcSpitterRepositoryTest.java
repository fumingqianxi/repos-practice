package com.itheima.spring实战.jdbc;

import com.itheima.spring实战.jdbc.config.JdbcConfig;
import com.itheima.spring实战.jdbc.dao.jdbc.JdbcSpitterRepositoryImpl;
import com.itheima.spring实战.jdbc.domain.JdbcSpitter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 胡磊
 * @since 2023/5/7 13:33
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JdbcConfig.class)
public class JdbcSpitterRepositoryTest {

  @Autowired
  private JdbcSpitterRepositoryImpl jdbcSpitterRepository;

  @Test
  public void addSpitterTest() {
    JdbcSpitter spitter = new JdbcSpitter();
    spitter.setUsername("test");
    spitter.setPassword("123456");
    spitter.setFullName("test");
    jdbcSpitterRepository.addSpitter(spitter);
  }

  @Test
  public void saveSpitterTest() {
    JdbcSpitter spitter = new JdbcSpitter();
    spitter.setUsername("testNoDataSource");
    spitter.setPassword("123456");
    spitter.setFullName("test");
    jdbcSpitterRepository.addSpitter(spitter);
  }

  @Test
  public void updateSpitterTest() {
    JdbcSpitter spitter = new JdbcSpitter();
    spitter.setUsername("testUpdate");
    spitter.setPassword("123456");
    spitter.setFullName("testUpdate");
    spitter.setId(1L);
    jdbcSpitterRepository.updateSpitter(spitter);
  }

  @Test
  public void findOneTest() {
    JdbcSpitter spitter = jdbcSpitterRepository.findOne(1);
    System.out.println(spitter);
  }
}
