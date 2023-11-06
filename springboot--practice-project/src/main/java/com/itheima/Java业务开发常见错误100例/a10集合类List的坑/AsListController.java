package com.itheima.Java业务开发常见错误100例.a10集合类List的坑;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Arrays.asList问题示例.
 *
 * @author 胡磊
 * @since 2023/11/6 13:20
 */
@Slf4j
@RestController
@RequestMapping("/as-list")
public class AsListController {

  /** http://localhost:45678/as-list/wrong1. */
  @GetMapping("/wrong1")
  public void wrong1() {
    int[] arr = {1, 2, 3};
    List list = Arrays.asList(arr);
    log.info("list:{} size:{} class:{}", list, list.size(), list.get(0).getClass());
  }

  /** http://localhost:45678/as-list/right1. */
  @GetMapping("/right1")
  public void right1() {
    int[] arr1 = {1, 2, 3};
    List list1 = Arrays.stream(arr1).boxed().collect(Collectors.toList());
    log.info("list:{} size:{} class:{}", list1, list1.size(), list1.get(0).getClass());

    Integer[] arr2 = {1, 2, 3};
    List list2 = Arrays.asList(arr2);
    log.info("list:{} size:{} class:{}", list2, list2.size(), list2.get(0).getClass());
  }

  /** http://localhost:45678/as-list/wrong2. */
  @GetMapping("/wrong2")
  public void wrong2() {
    String[] arr = {"1", "2", "3"};
    List list = Arrays.asList(arr);
    arr[1] = "4";
    try {
      list.add("5");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    log.info("arr:{} list:{}", Arrays.toString(arr), list);
  }

  /** http://localhost:45678/as-list/right2. */
  @GetMapping("/right2")
  public void right2() {
    String[] arr = {"1", "2", "3"};
    List list = new ArrayList(Arrays.asList(arr));
    arr[1] = "4";
    try {
      list.add("5");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    log.info("arr:{} list:{}", Arrays.toString(arr), list);
  }
}
