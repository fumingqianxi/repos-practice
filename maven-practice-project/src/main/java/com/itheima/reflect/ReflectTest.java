package com.itheima.reflect;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Properties;

public class ReflectTest {
    public static void main(String[] args) throws Exception{
        Properties properties = new Properties();
        InputStream is = ReflectTest.class.getClassLoader().getResourceAsStream("prop.properties");
        properties.load(is);
        String className = properties.getProperty("className");
        String methodName = properties.getProperty("methodName");
        Class cls = Class.forName(className);
        Object obj = cls.newInstance();
        Method method = cls.getMethod(methodName);
        method.invoke(obj);
    }
}
