package com.itheima;

import com.itheima.spring.day03_eesy_03springAOP.service.IAccountService;
import com.itheima.spring.day03_eesy_03springAOP.service.impl.AccountServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("day03_eesy_03springAOP.xml");
        IAccountService accountService = applicationContext.getBean("accountService", IAccountService.class);
        accountService.saveAccount();
    }

}
