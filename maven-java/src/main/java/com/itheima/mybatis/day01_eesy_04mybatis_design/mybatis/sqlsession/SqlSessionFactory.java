package com.itheima.mybatis.day01_eesy_04mybatis_design.mybatis.sqlsession;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 */
public interface SqlSessionFactory {

    /**
     * 用于打开一个新的SqlSession对象
     * @return
     */
    SqlSession openSession();
}
