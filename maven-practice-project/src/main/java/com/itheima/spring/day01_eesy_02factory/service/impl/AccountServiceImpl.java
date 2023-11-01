package com.itheima.spring.day01_eesy_02factory.service.impl;

import com.itheima.spring.day01_eesy_02factory.dao.AccountDao;
import com.itheima.spring.day01_eesy_02factory.dao.impl.AccountDaoImpl;
import com.itheima.spring.day01_eesy_02factory.factory.BeanFactory;
import com.itheima.spring.day01_eesy_02factory.service.AccountService;

public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao = (AccountDao) BeanFactory.getBean("accountDao");

    @Override
    public void saveAccount() {
        accountDao.saveAccount();
    }
}
