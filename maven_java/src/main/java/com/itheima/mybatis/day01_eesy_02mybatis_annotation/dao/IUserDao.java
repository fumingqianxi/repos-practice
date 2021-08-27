package com.itheima.mybatis.day01_eesy_02mybatis_annotation.dao;


import com.itheima.mybatis.day01_eesy_02mybatis_annotation.domain.User;
import org.apache.ibatis.annotations.Select;

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
    @Select("select * from user")
    List<User> findAll();
}
