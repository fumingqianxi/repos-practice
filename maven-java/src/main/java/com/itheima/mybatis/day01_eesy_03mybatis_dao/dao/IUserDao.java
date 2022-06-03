package com.itheima.mybatis.day01_eesy_03mybatis_dao.dao;


import com.itheima.mybatis.day01_eesy_03mybatis_dao.domain.User;

import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 *
 * 用户的持久层接口
 */
public interface IUserDao {

    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();
}
