package com.itheima.reflect;

import com.itheima.reflect.domain.Person;

public class ReflectDemo1 {
    public static void main(String[] args) throws ClassNotFoundException{
        Class cls = Class.forName("com.itheima.reflect.domain.Person");
        System.out.println(cls);
        Class cls1 = Person.class;
        System.out.println(cls1);
        Class cls2 = new Person().getClass();
        System.out.println(cls2);
    }
}
