package com.itheima.thread.CountdownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownLatchExample {
    public static void main(String[] args) throws InterruptedException{
        final int totalThread = 10;
        CountDownLatch countDownLatch = new CountDownLatch(totalThread);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < totalThread; i++) {
            service.execute(() -> {
                System.out.print("run..");
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("end");
        service.shutdown();
    }
}
