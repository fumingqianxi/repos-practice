package com.itheima.thread.waitandnotify;

public class Demo01WaitAndNotify {
    public static void main(String[] args) {
        Object obj = new Object();
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj) {
                    System.out.println("花了5秒钟做包子，告诉顾客可以吃了");
                    obj.notify();
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                synchronized (obj) {
                    System.out.println("告诉老板包子的种类和数量");
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("吃包子");
                }
            }
        }.start();
    }
}
