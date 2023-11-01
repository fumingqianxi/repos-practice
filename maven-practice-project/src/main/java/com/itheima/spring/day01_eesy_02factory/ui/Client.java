package com.itheima.spring.day01_eesy_02factory.ui;

import com.itheima.spring.day01_eesy_02factory.factory.BeanFactory;
import com.itheima.spring.day01_eesy_02factory.service.AccountService;
import com.itheima.spring.day01_eesy_02factory.service.impl.AccountServiceImpl;

public class Client {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            AccountService accountService = (AccountService) BeanFactory.getBean("accountService");
            System.out.println(accountService);
            accountService.saveAccount();
        }
    }
}
