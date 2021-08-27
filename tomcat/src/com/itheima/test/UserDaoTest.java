package com.itheima.test;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void testLogin() {
        User loginUser = new User();
        loginUser.setUsername("admin");
        loginUser.setPassword("admin11");
        UserDao dao = new UserDao();
        User user = dao.login(loginUser);
        System.out.println(user);
    }
}
