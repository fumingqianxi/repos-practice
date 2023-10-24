package com.itheima.Java业务开发常见错误100例.a02代码加锁;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 锁粒度错误使用示例.
 *
 * @author 胡磊
 * @since 2022/8/9 22:17
 */
@Slf4j
@RestController
@RequestMapping("lock-granularity")
public class LockGranularityController {

  private List<Integer> data = new ArrayList<>();

  /**
   * http://localhost:45678/lock-granularity/compare.
   *
   * @return .
   */
  @GetMapping("compare")
  public Map<String, Integer> compare() {
    Map<String, Integer> map = new HashMap<>();
    StopWatch stopWatch = new StopWatch();
    stopWatch.start("wrong");
    map.put("wrong", wrong());
    stopWatch.stop();
    stopWatch.start("right");
    map.put("right", right());
    stopWatch.stop();
    log.info(stopWatch.prettyPrint());
    return map;
  }

  /**
   * 错误的加锁方法.
   *
   * @return .
   */
  @GetMapping("wrong")
  public int wrong() {
    data = new ArrayList<>();
    long begin = System.currentTimeMillis();
    IntStream.rangeClosed(1, 100)
        .parallel()
        .forEach(
            i -> {
              // 加锁粒度太粗了
              synchronized (this) {
                slow();
                data.add(i);
              }
            });
    log.info("took:{}", System.currentTimeMillis() - begin);
    return data.size();
  }

  /**
   * 正确的加锁方法.
   *
   * @return .
   */
  @GetMapping("right")
  public int right() {
    data = new ArrayList<>();
    long begin = System.currentTimeMillis();
    IntStream.rangeClosed(1, 100)
        .parallel()
        .forEach(
            i -> {
              slow();
              // 只对List加锁
              synchronized (this) {
                data.add(i);
              }
            });
    log.info("took:{}", System.currentTimeMillis() - begin);
    return data.size();
  }

  // 不涉及共享资源的慢方法
  private void slow() {
    try {
      TimeUnit.MILLISECONDS.sleep(10);
    } catch (InterruptedException e) {
      log.error(e.getMessage(), e);
    }
  }
}
