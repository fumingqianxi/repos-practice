package com.itheima.thread.synchronize;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DemoSynchronize {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    public static void main(String[] args) {
//        show01();
//        show02();
//        show03();
        show04();
        lock.lock();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("==============");
        lock.unlock();
    }

    //同一个对象e1会进行同步
    public static void show01() {
        SynchronizedExample e1 = new SynchronizedExample();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> e1.func1());
        service.execute(() -> e1.func1());
        service.shutdown();
    }

    //两个对象不会进行同步
    public static void show02() {
        SynchronizedExample e1 = new SynchronizedExample();
        SynchronizedExample e2 = new SynchronizedExample();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> e1.func1());
        service.execute(() -> e2.func1());
    }


    public static void show03() {
        SynchronizedExample e1 = new SynchronizedExample();
        SynchronizedExample e2 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> e1.func3());
        executorService.execute(() -> e2.func3());
    }

    public static  void show04() {
        SynchronizedExample e1 = new SynchronizedExample(lock, condition);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> e1.func5());
    }
}
