package com.itheima.commonmistakes.a03threadpool;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import jodd.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 线程池OOM示例.
 *
 * @author 胡磊
 * @since 2023/10/24 13:48
 */
@Slf4j
@RestController
@RequestMapping("thread-pool-oom")
public class ThreadPoolOomController {

  private void printStats(ThreadPoolExecutor threadPool) {
    Executors.newSingleThreadScheduledExecutor()
        .scheduleAtFixedRate(
            () -> {
              log.info("=========================");
              log.info("Pool Size: {}", threadPool.getPoolSize());
              log.info("Active Threads: {}", threadPool.getActiveCount());
              log.info("Number of Tasks Completed: {}", threadPool.getCompletedTaskCount());
              log.info("Number of Tasks in Queue: {}", threadPool.getQueue().size());

              log.info("=========================");
            },
            0,
            1,
            TimeUnit.SECONDS);
  }

  /**
   * http://localhost:45678/thread-pool-oom/oom1. 未复现，再研究下.
   *
   * @throws InterruptedException .
   */
  @GetMapping("oom1")
  public void oom1() throws InterruptedException {
    ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
    // 打印线程池的信息，稍后我会解释这段代码
    printStats(threadPool);
    // 循环提交任务
    for (int i = 0; i < 100000000; i++) {
      threadPool.execute(
          () -> {
            // 模拟消耗一定内存
            String payload =
                IntStream.rangeClosed(1, 10000000)
                        .mapToObj(it -> "a")
                        .collect(Collectors.joining(""))
                    + UUID.randomUUID();
            try {
              TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
              log.info(e.getMessage(), e);
            }
            log.info(payload);
          });
    }
    threadPool.shutdown();
    threadPool.awaitTermination(1, TimeUnit.HOURS);
  }

  /**
   * http://localhost:45678/thread-pool-oom/oom2. 未复现，再研究下.
   *
   * @throws InterruptedException .
   */
  @GetMapping("oom2")
  public void oom2() throws InterruptedException {

    ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    printStats(threadPool);
    for (int i = 0; i < 100000000; i++) {
      threadPool.execute(
          () -> {
            String payload = UUID.randomUUID().toString();
            try {
              TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
              log.error(e.getMessage(), e);
            }
            log.info(payload);
          });
    }
    threadPool.shutdown();
    threadPool.awaitTermination(1, TimeUnit.HOURS);
  }

  /**
   * http://localhost:45678/thread-pool-oom/right.
   *
   * @return .
   * @throws InterruptedException .
   */
  @GetMapping("right")
  public int right() throws InterruptedException {
    AtomicInteger atomicInteger = new AtomicInteger();
    ThreadPoolExecutor threadPool =
        new ThreadPoolExecutor(
            2,
            5,
            5,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10),
            new ThreadFactoryBuilder().setNameFormat("demo-threadpool-%d").get(),
            new ThreadPoolExecutor.AbortPolicy());
    // threadPool.allowCoreThreadTimeOut(true);
    printStats(threadPool);
    IntStream.rangeClosed(1, 20)
        .forEach(
            i -> {
              try {
                TimeUnit.SECONDS.sleep(1);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              int id = atomicInteger.incrementAndGet();
              try {
                threadPool.submit(
                    () -> {
                      log.info("{} started", id);
                      try {
                        TimeUnit.SECONDS.sleep(10);
                      } catch (InterruptedException e) {
                        log.error(e.getMessage(), e);
                      }
                      log.info("{} finished", id);
                    });
              } catch (Exception ex) {
                log.error("error submitting task {}", id, ex);
                atomicInteger.decrementAndGet();
              }
            });

    TimeUnit.SECONDS.sleep(30);
    return atomicInteger.intValue();
  }
}
