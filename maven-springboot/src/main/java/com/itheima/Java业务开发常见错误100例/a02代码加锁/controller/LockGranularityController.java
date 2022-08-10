package com.itheima.Java业务开发常见错误100例.a02代码加锁.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author {胡磊}
 * @since 2022/8/9 22:17
 */
@Slf4j
@RestController
@RequestMapping("lockgranularity")
@Data
public class LockGranularityController {


  private List<Integer> data = new ArrayList<>();

  //不涉及共享资源的慢方法
  private void slow() {
    try {
      TimeUnit.MILLISECONDS.sleep(10);
    } catch (InterruptedException e) {
    }
  }

  //错误的加锁方法
  @GetMapping("wrong")
  public int wrong() {
    long begin = System.currentTimeMillis();
    IntStream.rangeClosed(1, 1000).parallel().forEach(i -> {
      //加锁粒度太粗了
      synchronized (this) {
        slow();
        data.add(i);
      }
    });
    log.info("took:{}", System.currentTimeMillis() - begin);
    return data.size();
  }

  //正确的加锁方法
  @GetMapping("right")
  public int right() {
    long begin = System.currentTimeMillis();
    IntStream.rangeClosed(1, 1000).parallel().forEach(i -> {
      slow();
      //只对List加锁
      synchronized (data) {
        data.add(i);
      }
    });
    log.info("took:{}", System.currentTimeMillis() - begin);
    return data.size();
  }
}
