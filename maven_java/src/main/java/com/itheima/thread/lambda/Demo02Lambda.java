package com.itheima.thread.lambda;

public class Demo02Lambda {
    public static void main(String[] args) {
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "新线程创建了");
        }).start();
    }
}
