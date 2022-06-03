package com.itheima.spring.day01_eesy_03spring.dao.impl;

import com.itheima.spring.day01_eesy_03spring.dao.IAccountDao;

/**
 * 账户的持久层实现类
 */
public class AccountDaoImpl implements IAccountDao {

    public  void saveAccount(){

        System.out.println("保存了账户");
    }
}
