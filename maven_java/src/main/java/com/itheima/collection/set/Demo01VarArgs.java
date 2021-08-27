package com.itheima.collection.set;

public class Demo01VarArgs {
    public static void main(String[] args) {
        System.out.println(add(1, 2, 3));
    }

    public static int add(int... arr) {
        int sum = 0;
        for (int a : arr) {
            sum = sum + a;
        }
        return sum;
    }
}
