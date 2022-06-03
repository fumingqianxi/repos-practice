package com.itheima.reflect;

import com.itheima.reflect.domain.Person;

import java.lang.reflect.Field;
import java.util.Objects;

public class ReflectDemo2 {
    public static void main(String[] args) throws Exception {
        Class cls = Person.class;
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        Field field = cls.getDeclaredField("age");
        field.setAccessible(true);
        Object age = field.get(new Person());
        System.out.println(age);
    }
}
