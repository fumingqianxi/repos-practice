package com.itheima.mybatis.custom.sqlsession;

/**
 * SqlSession工厂类.
 *
 * @author 胡磊
 * @since 2023/9/25 11:22
 */
public interface SqlSessionFactoryCustom {

    /**
     * 打开一个新的SqlSession对象.
     *
     * @return SqlSession对象
     */
    SqlSessionCustom openSession();
}
