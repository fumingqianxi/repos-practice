package com.itheima.commonmistakes.a12exception;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import jodd.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 线程池中异常问题示例.
 *
 * @author 胡磊
 * @since 2023/11/9 9:38
 */
@Slf4j
@RestController
@RequestMapping("/thread-pool-and-exception")
public class ThreadPoolAndExceptionController {

  static {
    Thread.setDefaultUncaughtExceptionHandler(
        (thread, throwable) -> log.error("Thread {} got exception", thread, throwable));
  }

  /**
   * http://localhost:45678/thread-pool-and-exception/execute.
   *
   * @throws InterruptedException .
   */
  @GetMapping("/execute")
  public void execute() throws InterruptedException {

    String prefix = "test";
    ExecutorService threadPool =
        Executors.newFixedThreadPool(
            1,
            new ThreadFactoryBuilder()
                .setNameFormat(prefix + "%d")
                .setUncaughtExceptionHandler(
                    (thread, throwable) ->
                        log.error("ThreadPool {} got exception", thread, throwable))
                .get());
    IntStream.rangeClosed(1, 10)
        .forEach(
            i ->
                threadPool.execute(
                    () -> {
                      if (i == 5) {
                        throw new RuntimeException("error");
                      }
                      log.info("I'm done : {}", i);
                    }));

    threadPool.shutdown();
    threadPool.awaitTermination(1, TimeUnit.HOURS);
  }

  /**
   * http://localhost:45678/thread-pool-and-exception/submit.
   *
   * @throws InterruptedException .
   */
  @GetMapping("/submit")
  public void submit() throws InterruptedException {

    String prefix = "test";
    ExecutorService threadPool =
        Executors.newFixedThreadPool(
            1, new ThreadFactoryBuilder().setNameFormat(prefix + "%d").get());
    IntStream.rangeClosed(1, 10)
        .forEach(
            i ->
                threadPool.submit(
                    () -> {
                      if (i == 5) {
                        throw new RuntimeException("error");
                      }
                      log.info("I'm done : {}", i);
                    }));

    threadPool.shutdown();
    threadPool.awaitTermination(1, TimeUnit.HOURS);
  }

  /**
   * http://localhost:45678/thread-pool-and-exception/submit-right.
   *
   * @throws InterruptedException .
   */
  @GetMapping("/submit-right")
  public void submitRight() throws InterruptedException {

    String prefix = "test";
    ExecutorService threadPool =
        Executors.newFixedThreadPool(
            1, new ThreadFactoryBuilder().setNameFormat(prefix + "%d").get());

    List<Future> tasks =
        IntStream.rangeClosed(1, 10)
            .mapToObj(
                i ->
                    threadPool.submit(
                        () -> {
                          if (i == 5) {
                            throw new RuntimeException("error");
                          }
                          log.info("I'm done : {}", i);
                        }))
            .collect(Collectors.toList());

    tasks.forEach(
        task -> {
          try {
            task.get();
          } catch (Exception e) {
            log.error("Got exception", e);
          }
        });
    threadPool.shutdown();
    threadPool.awaitTermination(1, TimeUnit.HOURS);
  }
}
