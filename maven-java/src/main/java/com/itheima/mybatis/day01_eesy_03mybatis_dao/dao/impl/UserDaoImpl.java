package com.itheima.mybatis.day01_eesy_03mybatis_dao.dao.impl;

import com.itheima.mybatis.day01_eesy_03mybatis_dao.dao.IUserDao;
import com.itheima.mybatis.day01_eesy_03mybatis_dao.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements IUserDao {
    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> findAll() {
        SqlSession session = factory.openSession();
        List<User> users = session.selectList("com.itheima.mybatis.day01_eesy_03mybatis_dao.dao.IUserDao.findAll");
        return users;
    }
}
