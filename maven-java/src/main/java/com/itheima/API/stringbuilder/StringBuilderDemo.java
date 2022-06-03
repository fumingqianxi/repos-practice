package com.itheima.API.stringbuilder;

public class StringBuilderDemo {
    public static void main(String[] args) {
//        demo01();
//        demo02();
        demo03();
    }

    /*
    StringBuilder和String可以相互转换:
        String->StringBuilder:可以使用StringBuilder的构造方法
            StringBuilder(String str) 构造一个字符串生成器，并初始化为指定的字符串内容。
        StringBuilder->String:可以使用StringBuilder中的toString方法
            public String toString()：将当前StringBuilder对象转换为String对象。
    */
    private static void demo03() {
        //String->StringBuilder
        String str = "hello";
        System.out.println("str:"+str);
        StringBuilder bu = new StringBuilder(str);
        //往StringBuilder中添加数据
        bu.append("world");
        System.out.println("bu:"+bu);

        //StringBuilder->String
        String s = bu.toString();
        System.out.println("s:"+s);
    }

    /*
    StringBuilder的常用方法:
        public StringBuilder append(...)：添加任意类型数据的字符串形式，并返回当前对象自身。
    */
    private static void demo02() {
        StringBuilder bu = new StringBuilder();
//        StringBuilder bu2 = bu.append("abc");
//        System.out.println(bu);
//        System.out.println(bu2);
//        System.out.println(bu == bu2);
        bu.append("abc");
        bu.append(1);
        bu.append(true);
        bu.append(8.8);
        bu.append('中');
        System.out.println(bu);
    }

    private static void demo01() {
        StringBuilder bu1 = new StringBuilder();
        System.out.println(bu1);
        StringBuilder bu2 = new StringBuilder("abc");
        System.out.println(bu2);
    }
}
