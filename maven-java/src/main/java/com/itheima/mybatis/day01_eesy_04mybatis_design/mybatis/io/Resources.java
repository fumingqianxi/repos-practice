package com.itheima.mybatis.day01_eesy_04mybatis_design.mybatis.io;

import java.io.InputStream;

public class Resources {

    public static InputStream getResourceAsStream(String filePath) {
        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }
}

