package com.itheima.spring.day01_eesy_03spring.service.impl;

import com.itheima.spring.day01_eesy_03spring.dao.IAccountDao;
import com.itheima.spring.day01_eesy_03spring.service.IAccountService;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao ;

    public AccountServiceImpl(){
        System.out.println("对象创建了");
    }

    public void  saveAccount(){
        System.out.println("Service方法执行了");
    }
}
