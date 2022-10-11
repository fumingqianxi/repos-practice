package com.itheima.Java业务开发常见错误100例.a02代码加锁.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 胡磊
 * @since 2022/8/9 11:05
 */
@Slf4j
@RestController
@RequestMapping("/interesting")
@Data
public class InterestingController {

  volatile int a = 1;
  volatile int b = 1;

  @GetMapping("test")
  public Map test() {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    InterestingController interesting = new InterestingController();
    executorService.execute(() -> interesting.add());
    executorService.execute(() -> interesting.compare());
    executorService.shutdown();
    Map<String, String> result = new HashMap();
    result.put("data", "ok");
    return result;
  }

  public void add() {
    log.info("add start");
    for (int i = 0; i < 10000; i++) {
      a++;
      b++;
    }
    log.info("add done");
  }

  public void compare() {
    log.info("compate start");
    for (int i = 0; i < 10000; i++) {
      if (a < b) {
        log.info("a:{},b:{},{}", a, b, a > b);
      }
    }
    log.info("compate done");
  }
}
