package com.itheima.Java业务开发常见错误100例.a03线程池.controller;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author {胡磊}
 * @since 2022/8/11 17:48
 */
@Slf4j
@RestController
@RequestMapping("thread-pool-oom")
public class ThreadPoolOOMController {

  private void printStats(ThreadPoolExecutor threadPool) {
    Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
      log.info("=========================");
      log.info("Pool Size: {}", threadPool.getPoolSize());
      log.info("Active Threads: {}", threadPool.getActiveCount());
      log.info("Number of Tasks Completed: {}", threadPool.getCompletedTaskCount());
      log.info("Number of Tasks in Queue: {}", threadPool.getQueue().size());

      log.info("=========================");
    }, 0, 1, TimeUnit.SECONDS);
  }

  @GetMapping("oom1")
  public void oom1() throws InterruptedException {
    ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
    //打印线程池的信息，稍后我会解释这段代码
    printStats(threadPool);
    for (int i = 0; i < 100000000; i++) {
      threadPool.execute(() -> {
        String payload = IntStream.rangeClosed(1, 1000000)
            .mapToObj(__ -> "a")
            .collect(Collectors.joining("")) + UUID.randomUUID().toString();
        try {
          TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
        }
        log.info(payload);
      });
    }

    threadPool.shutdown();
    threadPool.awaitTermination(1, TimeUnit.HOURS);
  }
}
