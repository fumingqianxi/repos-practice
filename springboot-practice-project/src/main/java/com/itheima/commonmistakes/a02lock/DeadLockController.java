package com.itheima.commonmistakes.a02lock;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 死锁示例.
 *
 * @author 胡磊
 * @since 2023/10/23 22:17
 */
@Slf4j
@RestController
@RequestMapping("/dead-lock")
public class DeadLockController {

  private ConcurrentHashMap<String, Item> items = new ConcurrentHashMap<>();

  public DeadLockController() {
    IntStream.range(0, 10).forEach(i -> items.put(("item" + i), new Item("item" + i)));
  }

  /**
   * http://localhost:45678/dead-lock/wrong.
   *
   * @return .
   */
  @GetMapping("wrong")
  public long wrong() {
    long begin = System.currentTimeMillis();
    long success =
        IntStream.rangeClosed(1, 100)
            .parallel()
            .mapToObj(
                i -> {
                  List<Item> cart = createCart();
                  return createOrder(cart);
                })
            .filter(result -> result)
            .count();
    log.info(
        "success:{} totalRemaining:{} took:{}ms items:{}",
        success,
        items.values().stream().map(item -> item.remaining).reduce(0, Integer::sum),
        System.currentTimeMillis() - begin,
        items);
    return success;
  }

  /**
   * http://localhost:45678/dead-lock/right.
   *
   * @return .
   */
  @GetMapping("right")
  public long right() {
    long begin = System.currentTimeMillis();
    long success =
        IntStream.rangeClosed(1, 100)
            .parallel()
            .mapToObj(
                i -> {
                  List<Item> cart =
                      createCart().stream()
                          .sorted(Comparator.comparing(Item::getName))
                          .collect(Collectors.toList());
                  return createOrder(cart);
                })
            .filter(result -> result)
            .count();
    log.info(
        "success:{} totalRemaining:{} took:{}ms items:{}",
        success,
        items.values().stream().map(item -> item.remaining).reduce(0, Integer::sum),
        System.currentTimeMillis() - begin,
        items);
    return success;
  }

  private List<Item> createCart() {
    return IntStream.rangeClosed(1, 3)
        .mapToObj(i -> items.get("item" + ThreadLocalRandom.current().nextInt(items.size())))
        .collect(Collectors.toList());
  }

  private boolean createOrder(List<Item> order) {
    // 存放拿到的所有锁
    List<ReentrantLock> locks = new ArrayList<>();

    for (Item item : order) {
      try {
        // 获取锁10秒超时
        if (item.lock.tryLock(10, TimeUnit.SECONDS)) {
          locks.add(item.lock);
        } else {
          locks.forEach(ReentrantLock::unlock);
          return false;
        }
      } catch (InterruptedException e) {
        log.error(e.getMessage(), e);
      }
    }
    // 获取到锁后执行业务逻辑
    try {
      order.forEach(item -> item.remaining--);
    } finally {
      locks.forEach(ReentrantLock::unlock);
    }
    return true;
  }

  @Data
  @RequiredArgsConstructor
  static class Item {
    final String name;
    int remaining = 1000;
    @ToString.Exclude ReentrantLock lock = new ReentrantLock();
  }
}
