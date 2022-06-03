package com.itheima.API.object;

/*
    java.lang.Object
    类 Object 是类层次结构的根(父)类。
    每个类(Person,Student...)都使用 Object 作为超(父)类。
    所有对象（包括数组）都实现这个类的方法。
 */
public class ToString {
    public static void main(String[] args) {
        Person p = new Person("小明", 18);
        System.out.println(p.toString());
        System.out.println(p);
    }
}
