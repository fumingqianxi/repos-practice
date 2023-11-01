package com.itheima.thread.java业务开发常见错误100例;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author 胡磊
 * @since 2022/8/6 12:38
 */
public class 线程并发执行的5中操作方式 {

  public static void main(String[] args) throws InterruptedException{
    System.out.println(thread(10000, 20));
    System.out.println(threadpool(10000, 20));
    System.out.println(forkjoin(10000, 20));
  }

  private static int thread (int taskCount, int threadCount) throws InterruptedException {
    //总操作计数器
    AtomicInteger atomicInteger = new AtomicInteger();
    //等待所有线程完成
    CountDownLatch countDownLatch = new CountDownLatch(threadCount);
    //使用IntStream把数字直接转为Thread
    IntStream.rangeClosed(1, threadCount).mapToObj(i -> new Thread(() -> {
      IntStream.rangeClosed(1, taskCount / threadCount).forEach(j -> atomicInteger.getAndIncrement());
      countDownLatch.countDown();
    })).forEach(Thread::start);
    countDownLatch.await();
    return atomicInteger.get();
  }

  private static int threadpool(int taskCount, int threadCount) throws InterruptedException{
    AtomicInteger atomicInteger = new AtomicInteger();
    ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
    IntStream.rangeClosed(1, taskCount).forEach(i -> executorService.execute(() -> atomicInteger.getAndIncrement()));
    executorService.shutdown();
    executorService.awaitTermination(1, TimeUnit.HOURS);
    return atomicInteger.get();
  }


  private static int forkjoin(int taskCount, int threadCount) throws InterruptedException {
    //总操作次数计数器
    AtomicInteger atomicInteger = new AtomicInteger();
    //自定义一个并行度=threadCount的ForkJoinPool
    ForkJoinPool forkJoinPool = new ForkJoinPool(threadCount);
    //所有任务直接提交到线程池处理
    forkJoinPool.execute(() -> IntStream.rangeClosed(1, taskCount).parallel().forEach(i -> increment(atomicInteger)));
    //提交关闭线程池申请，等待之前所有任务执行完成
    forkJoinPool.shutdown();
    forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
    //查询计数器当前值
    return atomicInteger.get();
  }


  private int stream(int taskCount, int threadCount) {
    //设置公共ForkJoinPool的并行度
    System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", String.valueOf(threadCount));
    //总操作次数计数器
    AtomicInteger atomicInteger = new AtomicInteger();
    //由于我们设置了公共ForkJoinPool的并行度，直接使用parallel提交任务即可
    IntStream.rangeClosed(1, taskCount).parallel().forEach(i -> increment(atomicInteger));
    //查询计数器当前值
    return atomicInteger.get();
  }


  private int completableFuture(int taskCount, int threadCount) throws InterruptedException,
      ExecutionException {
    //总操作次数计数器
    AtomicInteger atomicInteger = new AtomicInteger();
    //自定义一个并行度=threadCount的ForkJoinPool
    ForkJoinPool forkJoinPool = new ForkJoinPool(threadCount);
    //使用CompletableFuture.runAsync通过指定线程池异步执行任务
    CompletableFuture.runAsync(() -> IntStream.rangeClosed(1, taskCount).parallel().forEach(i -> increment(atomicInteger)), forkJoinPool).get();
    //查询计数器当前值
    return atomicInteger.get();
  }

  private static void increment(AtomicInteger atomicInteger) {
    atomicInteger.incrementAndGet();
    try {
      TimeUnit.MILLISECONDS.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
