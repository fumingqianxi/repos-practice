package com.itheima.file;

public class Demo01Recursion {
    public static void main(String[] args) {
        b(1);
    }

    private static void b(int i) {
        System.out.println(i);
        if (i == 20000) {
            return;
        }
        b(++i);
    }
}
