package com.itheima.spring.day01_eesy_02factory.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BeanFactory {
    private static Properties properties = new Properties();
    private static Map<String, Object> beans = new HashMap<>();

    static {
        InputStream inputStream = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
        try {
            properties.load(inputStream);
            Enumeration keys = properties.keys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement().toString();
                String beanPath = properties.getProperty(key);
                Object value = Class.forName(beanPath).newInstance();
                beans.put(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getBean(String beanName) {
        return beans.get(beanName);
    }
}
