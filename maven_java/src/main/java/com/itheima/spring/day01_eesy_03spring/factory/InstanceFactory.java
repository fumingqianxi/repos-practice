package com.itheima.spring.day01_eesy_03spring.factory;

import com.itheima.spring.day01_eesy_03spring.service.IAccountService;
import com.itheima.spring.day01_eesy_03spring.service.impl.AccountServiceImpl;

public class InstanceFactory {
    public IAccountService getAccountService() {
        return new AccountServiceImpl();
    }
}
