package com.itheima.spring实战.jdbc;

import com.itheima.spring实战.jdbc.config.JdbcConfig;
import com.itheima.spring实战.jdbc.dao.JdbcTemplateSpitterRepository;
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
public class JdbcTemplateSpitterRepositoryTest {

  @Autowired
  private JdbcTemplateSpitterRepository jdbcTemplateSpitterRepository;

  @Test
  public void saveTest() {
    JdbcSpitter spitter = new JdbcSpitter();
    spitter.setUsername("test");
    spitter.setPassword("123456");
    spitter.setFullName("test");
    spitter.setEmail("asdfa@gmail.com");
    spitter.setUpdateByEmail(true);
    jdbcTemplateSpitterRepository.save(spitter);
  }

  @Test
  public void findOneTest() {
    JdbcSpitter spitter = jdbcTemplateSpitterRepository.findOne(8);
    System.out.println(spitter);
  }
}
