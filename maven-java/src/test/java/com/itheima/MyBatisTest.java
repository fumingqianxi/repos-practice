package com.itheima;

import com.itheima.mybatis.day01_eesy_02mybatis_annotation.dao.IUserDao;
import com.itheima.mybatis.day01_eesy_02mybatis_annotation.domain.User;
import com.itheima.mybatis.day01_eesy_03mybatis_dao.dao.impl.UserDaoImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MyBatisTest {

    @Test
    public void day01_eesy_01mybatis() throws Exception {
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        com.itheima.mybatis.day01_eesy_01mybatis.dao.IUserDao userDao = session.getMapper(com.itheima.mybatis.day01_eesy_01mybatis.dao.IUserDao.class);
        List<com.itheima.mybatis.day01_eesy_01mybatis.domain.User> users = userDao.findAll();
        for (com.itheima.mybatis.day01_eesy_01mybatis.domain.User user : users) {
            System.out.println(user);
        }
        session.close();
        in.close();
    }

    @Test
    public void day01_eesy_02mybatis_annotation() throws Exception {
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        IUserDao userDao = session.getMapper(IUserDao.class);
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        session.close();
        in.close();
    }

    @Test
    public void day01_eesy_03mybatis_dao() throws Exception {
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        UserDaoImpl userDao = new UserDaoImpl(factory);
        List<com.itheima.mybatis.day01_eesy_03mybatis_dao.domain.User> users = userDao.findAll();
        for (com.itheima.mybatis.day01_eesy_03mybatis_dao.domain.User user : users) {
            System.out.println(user);
        }
        in.close();
    }

    @Test
    public void day01_eesy_04mybatis_design() throws Exception {
        InputStream in = com.itheima.mybatis.day01_eesy_04mybatis_design.mybatis.io.Resources.getResourceAsStream("SqlMapConfig.xml");
        com.itheima.mybatis.day01_eesy_04mybatis_design.mybatis.sqlsession.SqlSessionFactoryBuilder builder = new com.itheima.mybatis.day01_eesy_04mybatis_design.mybatis.sqlsession.SqlSessionFactoryBuilder();
        com.itheima.mybatis.day01_eesy_04mybatis_design.mybatis.sqlsession.SqlSessionFactory factory = builder.build(in);
        com.itheima.mybatis.day01_eesy_04mybatis_design.mybatis.sqlsession.SqlSession session = factory.openSession();
        com.itheima.mybatis.day01_eesy_04mybatis_design.dao.IUserDao userDao = session.getMapper(com.itheima.mybatis.day01_eesy_04mybatis_design.dao.IUserDao.class);
        List<com.itheima.mybatis.day01_eesy_04mybatis_design.domain.User> users = userDao.findAll();
        for (com.itheima.mybatis.day01_eesy_04mybatis_design.domain.User user : users) {
            System.out.println(user);
        }
        in.close();
    }

    /**
     * 测试保存操作
     */
    @Test
    public void testSave() throws Exception{
        com.itheima.mybatis.day02_eesy_01mybatisCRUD.domain.User user = new com.itheima.mybatis.day02_eesy_01mybatisCRUD.domain.User();
        user.setUsername("modify User property");
        user.setAddress("北京市顺义区");
        user.setSex("男");
        user.setBirthday(new Date());
        System.out.println("保存操作之前："+user);
        //5.执行保存方法
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        com.itheima.mybatis.day02_eesy_01mybatisCRUD.dao.IUserDao userDao = session.getMapper(com.itheima.mybatis.day02_eesy_01mybatisCRUD.dao.IUserDao.class);
        userDao.saveUser(user);

        System.out.println("保存操作之后："+user);
        session.commit();
    }

    /**
     * 测试保存操作
     */
    @Test
    public void testUpdate() throws Exception{
        com.itheima.mybatis.day02_eesy_01mybatisCRUD.domain.User user = new com.itheima.mybatis.day02_eesy_01mybatisCRUD.domain.User();
        user.setId(50);
        user.setUsername("modify User property");
        user.setAddress("北京市顺义区");
        user.setSex("女");
        user.setBirthday(new Date());
        System.out.println("保存操作之前："+user);
        //5.执行保存方法
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        com.itheima.mybatis.day02_eesy_01mybatisCRUD.dao.IUserDao userDao = session.getMapper(com.itheima.mybatis.day02_eesy_01mybatisCRUD.dao.IUserDao.class);
        userDao.updateUser(user);

        System.out.println("保存操作之后："+user);
        session.commit();
    }

    @Test
    public void testDelete() throws Exception{
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        com.itheima.mybatis.day02_eesy_01mybatisCRUD.dao.IUserDao userDao = session.getMapper(com.itheima.mybatis.day02_eesy_01mybatisCRUD.dao.IUserDao.class);
        userDao.deleteUser(52);
        session.commit();
    }

    /**
     * 测试查询单个用户
     */
    @Test
    public void testFindOne() throws Exception{
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        com.itheima.mybatis.day02_eesy_01mybatisCRUD.dao.IUserDao userDao = session.getMapper(com.itheima.mybatis.day02_eesy_01mybatisCRUD.dao.IUserDao.class);
        System.out.println(userDao.findById(50));
    }

    @Test
    public void testFindByName() throws Exception{
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        com.itheima.mybatis.day02_eesy_01mybatisCRUD.dao.IUserDao userDao = session.getMapper(com.itheima.mybatis.day02_eesy_01mybatisCRUD.dao.IUserDao.class);
        System.out.println(userDao.findByName("%王%"));
    }
}
