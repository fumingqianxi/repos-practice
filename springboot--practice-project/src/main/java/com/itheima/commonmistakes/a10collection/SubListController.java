package com.itheima.commonmistakes.a10collection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * subList问题示例.
 *
 * @author 胡磊
 * @since 2023/11/6 13:20
 */
@Slf4j
@RestController
@RequestMapping("/sub-list")
public class SubListController {

  private static List<List<Integer>> data = new ArrayList<>();

  /** http://localhost:45678/sub-list/oom. */
  @GetMapping("/oom")
  public void oom() {
    for (int i = 0; i < 1000; i++) {
      List<Integer> rawList = IntStream.rangeClosed(1, 100000).boxed().collect(Collectors.toList());
      data.add(rawList.subList(0, 1));
    }
  }

  /** http://localhost:45678/sub-list/oom-fix. */
  @GetMapping("/oom-fix")
  public void oomFix () {
    for (int i = 0; i < 1000; i++) {
      List<Integer> rawList = IntStream.rangeClosed(1, 100000).boxed().collect(Collectors.toList());
      data.add(new ArrayList<>(rawList.subList(0, 1)));
    }
    log.info("done");
  }

  /** http://localhost:45678/sub-list/wrong. */
  @GetMapping("/wrong")
  public void wrong() {
    List<Integer> list = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
    List<Integer> subList = list.subList(1, 4);
    System.out.println(subList);
    subList.remove(1);
    System.out.println(list);
    list.add(0);
    try {
      subList.forEach(System.out::println);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /** http://localhost:45678/sub-list/right1. */
  @GetMapping("/right1")
  public void right1() {
    log.info(Thread.currentThread().getName());
    List<Integer> list = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
    List<Integer> subList = new ArrayList<>(list.subList(1, 4));
    System.out.println(subList);
    subList.remove(1);
    System.out.println(list);
    list.add(0);
    subList.forEach(System.out::println);
  }

  /** http://localhost:45678/sub-list/right2. */
  @GetMapping("/right2")
  public void right2() {
    log.info(Thread.currentThread().getName());
    List<Integer> list = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
    List<Integer> subList = list.stream().skip(1).limit(3).collect(Collectors.toList());
    System.out.println(subList);
    subList.remove(1);
    System.out.println(list);
    list.add(0);
    subList.forEach(System.out::println);
  }
}
