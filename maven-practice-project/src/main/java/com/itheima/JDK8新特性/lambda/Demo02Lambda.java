package com.itheima.JDK8新特性.lambda;

public class Demo02Lambda {
    public static void main(String[] args) {
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "新线程创建了");
        }).start();
    }
}
