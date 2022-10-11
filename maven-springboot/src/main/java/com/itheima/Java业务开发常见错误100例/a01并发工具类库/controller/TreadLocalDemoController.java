package com.itheima.Java业务开发常见错误100例.a01并发工具类库.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 胡磊
 * @since 2022/8/3 20:53
 */
@RestController
@RequestMapping("/threadLocal")
public class TreadLocalDemoController {

  private static final ThreadLocal<Integer> currentUser = ThreadLocal.withInitial(() -> null);

  @GetMapping("wrong")
  public Map wrong(@RequestParam("userId") Integer userId) {
    //设置用户信息前查询一次ThreadLocal中的用户信息
    String before = Thread.currentThread().getName() + ":" + currentUser.get();
    currentUser.set(userId);
    //设置用户信息之后再查询一次ThreadLocal中的用户信息
    String after  = Thread.currentThread().getName() + ":" + currentUser.get();
    //汇总输出两次查询结果
    Map result = new HashMap();
    result.put("before", before);
    result.put("after", after);
    return result;
  }

  @GetMapping("right")
  public Map right(@RequestParam("userId") Integer userId) {
    //设置用户信息前查询一次ThreadLocal中的用户信息
    String before = Thread.currentThread().getName() + ":" + currentUser.get();
    currentUser.set(userId);
    try {
      //设置用户信息之后再查询一次ThreadLocal中的用户信息
      String after  = Thread.currentThread().getName() + ":" + currentUser.get();
      //汇总输出两次查询结果
      Map result = new HashMap();
      result.put("before", before);
      result.put("after", after);
      return result;
    } finally {
      currentUser.remove();
    }
  }
}
