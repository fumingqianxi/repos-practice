package com.itheima.mybatis.day01_eesy_04mybatis_design.mybatis.sqlsession;

import com.itheima.mybatis.day01_eesy_04mybatis_design.mybatis.cfg.Configuration;
import com.itheima.mybatis.day01_eesy_04mybatis_design.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import com.itheima.mybatis.day01_eesy_04mybatis_design.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream config) {
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        return  new DefaultSqlSessionFactory(cfg);
    }
}
