package com.itheima.mybatis.dao.impl;

import com.itheima.mybatis.dao.UserDao;
import com.itheima.mybatis.dto.UserDto;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 用户Dao实现.
 *
 * @author 胡磊
 * @since 2023/9/25 10:02
 */
public class UserDaoImpl implements UserDao {

  private SqlSessionFactory sqlSessionFactory;

  public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public List<UserDto> findAll() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    List<UserDto> userDtos = sqlSession.selectList("com.itheima.mybatis.dao.UserDao.findAll");
    sqlSession.close();
    return userDtos;
  }
}
