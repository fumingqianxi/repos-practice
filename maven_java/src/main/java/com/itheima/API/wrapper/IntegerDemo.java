package com.itheima.API.wrapper;

import java.util.ArrayList;

public class IntegerDemo {
    public static void main(String[] args) {
//        demo01();
//        demo02();
        demo03();
    }

    /*
    装箱:把基本类型的数据,包装到包装类中(基本类型的数据->包装类)
        构造方法:
            Integer(int value) 构造一个新分配的 Integer 对象，它表示指定的 int 值。
            Integer(String s) 构造一个新分配的 Integer 对象，它表示 String 参数所指示的 int 值。
                传递的字符串,必须是基本类型的字符串,否则会抛出异常 "100" 正确  "a" 抛异常
        静态方法:
            static Integer valueOf(int i) 返回一个表示指定的 int 值的 Integer 实例。
            static Integer valueOf(String s) 返回保存指定的 String 的值的 Integer 对象。
    拆箱:在包装类中取出基本类型的数据(包装类->基本类型的数据)
        成员方法:
            int intValue() 以 int 类型返回该 Integer 的值。
    */
    private static void demo01() {
        Integer in1 = new Integer(1);
        System.out.println(in1);

        Integer in2 = new Integer("1");
        System.out.println(in2);

        Integer in3 = Integer.valueOf(1);
        System.out.println(in3);

        Integer in4 = Integer.valueOf("1");
        System.out.println(in4);

        int i = in1.intValue();
        System.out.println(i);
    }

    /*
        自动装箱与自动拆箱:基本类型的数据和包装类之间可以自动的相互转换
        JDK1.5之后出现的新特性
    */
    private static void demo02() {
        Integer in = 1;
        in = in + 2;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        int a = list.get(0);
    }

    /*
    基本类型与字符串类型之间的相互转换
    基本类型->字符串(String)
        1.基本类型的值+""  最简单的方法(工作中常用)
        2.包装类的静态方法toString(参数),不是Object类的toString() 重载
            static String toString(int i) 返回一个表示指定整数的 String 对象。
        3.String类的静态方法valueOf(参数)
            static String valueOf(int i) 返回 int 参数的字符串表示形式。
    字符串(String)->基本类型
        使用包装类的静态方法parseXXX("字符串");
            Integer类: static int parseInt(String s)
            Double类: static double parseDouble(String s)
    */
    private static void demo03() {
        int i1 = 100;
        String s1 = i1 + "";
        System.out.println(s1 + 200);

        String s2 = Integer.toString(i1);
        System.out.println(s2 + 200);

        String s3 = String.valueOf(i1);
        System.out.println(s3 + 200);

        int i = Integer.parseInt(s1);
        System.out.println(i - 10);

        int i2 = Integer.valueOf(s1);
        System.out.println(i2 - 10);
    }
}
