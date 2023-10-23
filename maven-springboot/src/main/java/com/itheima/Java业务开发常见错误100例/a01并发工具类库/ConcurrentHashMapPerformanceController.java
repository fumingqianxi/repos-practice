package com.itheima.Java业务开发常见错误100例.a01并发工具类库;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ConcurrentHashMap性能问题示例.
 *
 * @author 胡磊
 * @since 2023/10/23 17:12
 */
@Slf4j
@RestController
@RequestMapping("/concurrent-hash-map-performance")
public class ConcurrentHashMapPerformanceController {

  // 循环次数
  private static int LOOP_COUNT = 10000000;
  // 线程数量
  private static int THREAD_COUNT = 10;
  // 元素数量
  private static int ITEM_COUNT = 10;

  /**
   * http://localhost:45678/concurrent-hash-map-performance/compare.
   *
   * @return .
   * @throws InterruptedException .
   */
  @GetMapping("/compare")
  public String compare() throws InterruptedException {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start("normaluse");
    Map<String, Long> normaluse = normaluse();
    stopWatch.stop();
    // 校验元素数量
    Assert.isTrue(normaluse.size() == ITEM_COUNT, "normaluse size error");
    // 校验累计总数
    Assert.isTrue(
        normaluse.values().stream().mapToLong(l -> l).reduce(0, Long::sum) == LOOP_COUNT,
        "normaluse count error");
    stopWatch.start("gooduse");
    Map<String, Long> gooduse = gooduse();
    stopWatch.stop();
    Assert.isTrue(gooduse.size() == ITEM_COUNT, "gooduse size error");
    Assert.isTrue(
        gooduse.values().stream().mapToLong(l -> l).reduce(0, Long::sum) == LOOP_COUNT,
        "gooduse count error");
    log.info(stopWatch.prettyPrint());
    return "OK";
  }

  private Map<String, Long> normaluse() throws InterruptedException {
    ConcurrentHashMap<String, Long> freqs = new ConcurrentHashMap<>(ITEM_COUNT);
    ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
    forkJoinPool.execute(
        () ->
            IntStream.rangeClosed(1, LOOP_COUNT)
                .parallel()
                .forEach(
                    i -> {
                      // 获得一个随机的Key
                      String key = "item" + new Random().nextInt(ITEM_COUNT);
                      synchronized (freqs) {
                        if (freqs.containsKey(key)) {
                          // Key存在则+1
                          freqs.put(key, freqs.get(key) + 1);
                        } else {
                          // Key不存在则初始化为1
                          freqs.put(key, 1L);
                        }
                      }
                    }));
    forkJoinPool.shutdown();
    forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
    return freqs;
  }

  private Map<String, Long> gooduse() throws InterruptedException {
    ConcurrentHashMap<String, LongAdder> freqs = new ConcurrentHashMap<>(ITEM_COUNT);
    ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
    forkJoinPool.execute(
        () ->
            IntStream.rangeClosed(1, LOOP_COUNT)
                .parallel()
                .forEach(
                    i -> {
                      String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
                      // 利用computeIfAbsent()方法来实例化LongAdder，然后利用LongAdder来进行线程安全计数
                      freqs.computeIfAbsent(key, k -> new LongAdder()).increment();
                    }));
    forkJoinPool.shutdown();
    forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
    // 因为我们的Value是LongAdder而不是Long，所以需要做一次转换才能返回
    return freqs.entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().longValue()));
  }
}
