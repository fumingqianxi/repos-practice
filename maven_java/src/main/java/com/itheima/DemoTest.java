package com.itheima;

import java.text.ParseException;
import java.util.Objects;
import java.util.UUID;

public class DemoTest {
    public static void main(String[] args) throws ParseException {
        System.out.println("debug开始");
        fun();
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        System.out.println(null + ";" + "adasd");
    }

    public static void fun() {
        for (int i = 0; i < 10; i++) {
            System.out.println("fun: " + i);
        }
    }
}
