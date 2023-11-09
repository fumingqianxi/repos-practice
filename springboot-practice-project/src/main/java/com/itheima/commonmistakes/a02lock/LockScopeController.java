package com.itheima.commonmistakes.a02lock;

import java.util.stream.IntStream;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 锁的范围错误使用示例.
 *
 * @author 胡磊
 * @since 2022/8/9 11:05
 */
@Slf4j
@RestController
@RequestMapping("/lock-scope")
public class LockScopeController {

  /**
   * http://localhost:45678/lock-scope/wrong.
   *
   * @return .
   */
  @GetMapping("/wrong")
  public String wrong() {
    Interesting interesting = new Interesting();
    new Thread(interesting::add).start();
    new Thread(interesting::compare).start();
    return "OK";
  }

  /**
   * http://localhost:45678/lock-scope/right.
   *
   * @return .
   */
  @GetMapping("/right")
  public String right() {
    Interesting interesting = new Interesting();
    new Thread(interesting::add).start();
    new Thread(interesting::compareRight).start();
    return "OK";
  }

  /**
   * http://localhost:45678/lock-scope/wrong-scope.
   *
   * @param count .
   * @return .
   */
  @GetMapping("/wrong-scope")
  public int wrongScope(@RequestParam(value = "count", defaultValue = "1000000") int count) {
    Data.reset();
    IntStream.rangeClosed(1, count).parallel().forEach(i -> new Data().wrong());
    return Data.getCounter();
  }

  /**
   * http://localhost:45678/lock-scope/right-scope.
   *
   * @param count .
   * @return .
   */
  @GetMapping("/right-scope")
  public int rightScope(@RequestParam(value = "count", defaultValue = "1000000") int count) {
    Data.reset();
    IntStream.rangeClosed(1, count).parallel().forEach(i -> new Data().right());
    return Data.getCounter();
  }

  @Slf4j
  static class Interesting {

    volatile int a = 1;
    volatile int b = 1;

    public synchronized void add() {
      log.info("add start");
      for (int i = 0; i < 1000000; i++) {
        a++;
        b++;
      }
      log.info("add done");
    }

    public void compare() {
      log.info("compare start");
      for (int i = 0; i < 1000000; i++) {
        if (a < b) {
          log.info("a:{},b:{},{}", a, b, a > b);
          // 最后的a>b应该始终是false的吗？
        }
      }
      log.info("compare done");
    }

    public synchronized void compareRight() {
      log.info("compare start");
      for (int i = 0; i < 1000000; i++) {
        Assert.isTrue(a == b, "不相等");
        if (a < b) {
          log.info("a:{},b:{},{}", a, b, a > b);
        }
      }
      log.info("compare done");
    }
  }

  static class Data {
    @Getter private static int counter = 0;
    private static Object locker = new Object();

    public static int reset() {
      counter = 0;
      return counter;
    }

    public synchronized void wrong() {
      counter++;
    }

    public void right() {
      synchronized (locker) {
        counter++;
      }
    }
  }
}
