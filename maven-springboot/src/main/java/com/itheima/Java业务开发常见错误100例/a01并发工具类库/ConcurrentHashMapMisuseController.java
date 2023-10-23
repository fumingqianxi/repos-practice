package com.itheima.Java业务开发常见错误100例.a01并发工具类库;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ConcurrentHashMap错误使用示例.
 *
 * @author 胡磊
 * @since 2022/8/7 15:04
 */
@Slf4j
@RestController
@RequestMapping("/concurrent-hash-map")
public class ConcurrentHashMapMisuseController {

  // 线程个数
  private static final int THREAD_COUNT = 10;
  // 总元素数量
  private static final int ITEM_COUNT = 1000;

  // 获得一个指定元素数量模拟数据的ConcurrentHashMap
  private ConcurrentHashMap<String, Long> getData(int count) {
    return LongStream.rangeClosed(1, count)
        .boxed()
        .collect(
            Collectors.toConcurrentMap(
                i -> UUID.randomUUID().toString(),
                Function.identity(),
                (o1, o2) -> o1,
                ConcurrentHashMap::new));
  }

  /**
   * http://localhost:45678/concurrent-hash-map/wrong.
   *
   * @return .
   * @throws InterruptedException .
   */
  @GetMapping("/wrong")
  public String wrong() throws InterruptedException {
    ConcurrentHashMap<String, Long> concurrentHashMap = getData(ITEM_COUNT - 100);
    // 初始900个元素
    log.info("init size:{}", concurrentHashMap.size());

    // 10个线程的线程池
    ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
    // 使用线程池并发处理逻辑
    forkJoinPool.execute(
        () ->
            // 10个并行任务，会分配到不同线程上
            IntStream.rangeClosed(1, 10)
                .parallel()
                .forEach(
                    i -> {
                      // 查询还需要补充多少个元素
                      int gap = ITEM_COUNT - concurrentHashMap.size();
                      log.info("尚缺{}个，线程：{}", gap, Thread.currentThread().getName());
                      // 补充元素
                      concurrentHashMap.putAll(getData(gap));
                    }));
    // 等待所有任务完成
    forkJoinPool.shutdown();
    forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
    // 最后元素个数会是1000吗？
    log.info("finish size:{}", concurrentHashMap.size());
    return "OK";
  }

  /**
   * http://localhost:45678/concurrent-hash-map/right.
   *
   * @return .
   * @throws InterruptedException .
   */
  @GetMapping("/right")
  public String right() throws InterruptedException {
    ConcurrentHashMap<String, Long> concurrentHashMap = getData(ITEM_COUNT - 100);
    // 初始900个元素
    log.info("init size:{}", concurrentHashMap.size());

    ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
    // 使用线程池并发处理逻辑
    forkJoinPool.execute(
        () ->
            IntStream.rangeClosed(1, 10)
                .parallel()
                .forEach(
                    i -> {
                      synchronized (concurrentHashMap) {
                        // 查询还需要补充多少个元素
                        int gap = ITEM_COUNT - concurrentHashMap.size();
                        log.info("尚缺{}个，线程：{}", gap, Thread.currentThread().getName());
                        // 补充元素
                        concurrentHashMap.putAll(getData(gap));
                      }
                    }));
    // 等待所有任务完成
    forkJoinPool.shutdown();
    forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
    // 最后元素个数会是1000吗？
    log.info("finish size:{}", concurrentHashMap.size());
    return "OK";
  }
}
