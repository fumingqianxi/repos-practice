package com.itheima.mybatis;

import com.itheima.mybatis.custom.io.ResourcesCustom;
import com.itheima.mybatis.custom.sqlsession.SqlSessionCustom;
import com.itheima.mybatis.custom.sqlsession.SqlSessionFactoryBuilderCustom;
import com.itheima.mybatis.custom.sqlsession.SqlSessionFactoryCustom;
import com.itheima.mybatis.dao.UserDao;
import com.itheima.mybatis.dao.UserMapper;
import com.itheima.mybatis.dao.impl.UserDaoImpl;
import com.itheima.mybatis.dto.UserDto;
import java.io.InputStream;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

/**
 * Mybatis测试.
 *
 * @author 胡磊
 * @since 2023/9/22 17:55
 */
@Slf4j
public class MybatisTest {

  @Test
  public void testFindAll() throws Exception {
    InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    SqlSessionFactory sqlSessionFactory = builder.build(in);
    SqlSession sqlSession = sqlSessionFactory.openSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    List<UserDto> list = mapper.findAll();
    for (UserDto dto : list) {
      log.info("用户信息：{}", dto);
    }
    sqlSession.close();
    in.close();
  }

  @Test
  public void testFindAllByAnno() throws Exception {
    InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    SqlSessionFactory sqlSessionFactory = builder.build(in);
    SqlSession sqlSession = sqlSessionFactory.openSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    List<UserDto> list = mapper.findAllByAnno();
    for (UserDto dto : list) {
      log.info("用户信息：{}", dto);
    }
    sqlSession.close();
    in.close();
  }

  /**
   * 自定义mapper实现类测试.
   *
   * @throws Exception
   */
  @Test
  public void testFindAllCustomImpl() throws Exception {
    InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    SqlSessionFactory sqlSessionFactory = builder.build(in);
    UserDao userDao = new UserDaoImpl(sqlSessionFactory);
    List<UserDto> list = userDao.findAll();
    for (UserDto dto : list) {
      log.info("用户信息：{}", dto);
    }
    in.close();
  }

  /**
   * 自定义Mybatis框架测试.
   *
   * @throws Exception
   */
  @Test
  public void testFindAllCustomMybatis() throws Exception {
    InputStream in = ResourcesCustom.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactoryBuilderCustom builderCustom = new SqlSessionFactoryBuilderCustom();
    SqlSessionFactoryCustom sqlSessionFactoryCustom = builderCustom.build(in);
    SqlSessionCustom sqlSessionCustom = sqlSessionFactoryCustom.openSession();
    UserMapper mapper = sqlSessionCustom.getMapper(UserMapper.class);
    // 测试xml配置
    List<UserDto> list = mapper.findAll();
    Assert.assertNotNull(list);
    log.info("用户总数量为：{}", list.size());
    for (UserDto dto : list) {
      log.info("用户信息：{}", dto);
    }
    log.info("===============分割线==================");
    // 测试注解配置
    List<UserDto> list2 = mapper.findAllByAnno();
    Assert.assertNotNull(list2);
    log.info("用户总数量为：{}", list.size());
    for (UserDto dto : list2) {
      log.info("用户信息：{}", dto);
    }
    sqlSessionCustom.close();
    in.close();
  }
}
