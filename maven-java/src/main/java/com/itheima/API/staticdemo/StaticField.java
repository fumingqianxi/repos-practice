package com.itheima.API.staticdemo;

public class StaticField {
    public static void main(String[] args) {
        Student one = new Student("小明", 19);
        one.room = "五班";
        System.out.print(one);
        System.out.println(one.room);
        Student two = new Student("小李", 100);
        System.out.print(two);
        System.out.println(two.room);
    }
}
