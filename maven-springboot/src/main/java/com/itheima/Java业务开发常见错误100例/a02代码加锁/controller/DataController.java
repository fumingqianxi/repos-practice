package com.itheima.Java业务开发常见错误100例.a02代码加锁.controller;

import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author {胡磊}
 * @since 2022/8/9 20:56
 */
@Slf4j
@RestController
@RequestMapping("data")
public class DataController {

  public static int getCounter() {
    return counter;
  }

  private static int counter = 0;
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


  @GetMapping("wrong")
  public int wrong(@RequestParam(value = "count", defaultValue = "1000000") int count) {
    DataController.reset();
    //多线程循环一定次数调用Data类不同实例的wrong方法
    IntStream.rangeClosed(1, count).parallel().forEach(i -> new DataController().wrong());
    return DataController.getCounter();
  }

  @GetMapping("right")
  public int right(@RequestParam(value = "count", defaultValue = "1000000") int count) {
    DataController.reset();
    //多线程循环一定次数调用Data类不同实例的wrong方法
    IntStream.rangeClosed(1, count).parallel().forEach(i -> new DataController().right());
    log.info("right方法执行了");
    return DataController.getCounter();
  }
}
