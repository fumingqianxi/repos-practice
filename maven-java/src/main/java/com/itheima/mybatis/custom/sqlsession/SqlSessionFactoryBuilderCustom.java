package com.itheima.mybatis.custom.sqlsession;

import com.itheima.mybatis.custom.cfg.ConfigurationCustom;
import com.itheima.mybatis.custom.sqlsession.defaults.DefaultSqlSessionFactoryCustom;
import com.itheima.util.mybatis.XMLConfigBuilderUtil;
import java.io.InputStream;

/**
 * SqlSessionFactory构建类.
 *
 * @author 胡磊
 * @since 2023/9/25 11:22
 */
public class SqlSessionFactoryBuilderCustom {

    public SqlSessionFactoryCustom build(InputStream config) {
        ConfigurationCustom cfg = XMLConfigBuilderUtil.loadConfiguration(config);
        return  new DefaultSqlSessionFactoryCustom(cfg);
    }
}
