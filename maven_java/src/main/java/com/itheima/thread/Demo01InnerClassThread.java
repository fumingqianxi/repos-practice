package com.itheima.thread;

public class Demo01InnerClassThread {
    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName() + "-->黑马");
                }
            }
        }.start();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName() + "-->程序员");
                }
            }
        };
        new Thread(r).start();
    }
}
