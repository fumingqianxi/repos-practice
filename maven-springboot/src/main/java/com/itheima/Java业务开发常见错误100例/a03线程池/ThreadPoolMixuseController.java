package com.itheima.Java业务开发常见错误100例.a03线程池;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Collections;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;
import jodd.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 线程池混用错误示例.
 *
 * @author 胡磊
 * @since 2023/10/24 13:48
 */
@Slf4j
@RestController
@RequestMapping("thread-pool-mixuse")
public class ThreadPoolMixuseController {

  private static ThreadPoolExecutor threadPool =
      new ThreadPoolExecutor(
          2,
          2,
          1,
          TimeUnit.HOURS,
          new ArrayBlockingQueue<>(100),
          new ThreadFactoryBuilder().setNameFormat("batchfileprocess-threadpool-%d").get(),
          new ThreadPoolExecutor.CallerRunsPolicy());

  private static ThreadPoolExecutor asyncCalcThreadPool =
      new ThreadPoolExecutor(
          200,
          200,
          1,
          TimeUnit.HOURS,
          new ArrayBlockingQueue<>(1000),
          new ThreadFactoryBuilder().setNameFormat("asynccalc-threadpool-%d").get());

  /**
   * http://localhost:45678/thread-pool-mixuse/wrong.
   *
   * @return .
   * @throws ExecutionException .
   * @throws InterruptedException .
   */
  @GetMapping("wrong")
  public int wrong() throws ExecutionException, InterruptedException {
    return threadPool.submit(calcTask()).get();
  }

  /**
   * http://localhost:45678/thread-pool-mixuse/right.
   *
   * @return .
   * @throws ExecutionException .
   * @throws InterruptedException .
   */
  @GetMapping("right")
  public int right() throws ExecutionException, InterruptedException {
    return asyncCalcThreadPool.submit(calcTask()).get();
  }

  /** 模拟后台一直文件批处理，将线程池一直占用，这时混用该线程池就会导致问题. */
//  @PostConstruct
  public void init() {
    printStats(threadPool);

    new Thread(
            () -> {
              String payload =
                  IntStream.rangeClosed(1, 1_000_000)
                      .mapToObj(__ -> "a")
                      .collect(Collectors.joining(""));
              while (true) {
                threadPool.execute(
                    () -> {
                      try {
                        Files.write(
                            Paths.get("demo.txt"),
                            Collections.singletonList(LocalTime.now().toString() + ":" + payload),
                            UTF_8,
                            CREATE,
                            TRUNCATE_EXISTING);
                      } catch (IOException e) {
                        e.printStackTrace();
                      }
                      log.info("batch file processing done");
                    });
              }
            })
        .start();
  }

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

  private Callable<Integer> calcTask() {
    return () -> {
      TimeUnit.MILLISECONDS.sleep(10);
      return 1;
    };
  }
}
