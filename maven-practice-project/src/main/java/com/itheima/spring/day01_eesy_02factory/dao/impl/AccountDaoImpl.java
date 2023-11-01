package com.itheima.spring.day01_eesy_02factory.dao.impl;

import com.itheima.spring.day01_eesy_02factory.dao.AccountDao;

public class AccountDaoImpl implements AccountDao {
    @Override
    public void saveAccount() {
        System.out.println("执行保存账户操作");
    }
}
