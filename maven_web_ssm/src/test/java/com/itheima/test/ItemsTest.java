package com.itheima.test;

import com.itheima.dao.ItemsDao;
import com.itheima.domain.Items;
import com.itheima.service.ItemsService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ItemsTest {
    @Test
    public void findById() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        ItemsDao itemsDao = ac.getBean(ItemsDao.class);
//        Items items = itemsDao.findById(1);
//        System.out.println(items.getName());
        ItemsService itemsService = ac.getBean(ItemsService.class);
        System.out.println(itemsService.findById(1).getName());
    }
}
