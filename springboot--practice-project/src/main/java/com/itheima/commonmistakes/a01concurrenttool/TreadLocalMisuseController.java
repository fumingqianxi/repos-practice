package com.itheima.commonmistakes.a01concurrenttool;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ThreadLocal错误使用示例.
 *
 * @author 胡磊
 * @since 2022/8/3 20:53
 */
@RestController
@RequestMapping("/thread-local")
public class TreadLocalMisuseController {

  private static final ThreadLocal<Integer> currentUser = ThreadLocal.withInitial(() -> null);

  /**
   * http://localhost:45678/thread-local/wrong?userId=222.
   *
   * @param userId .
   * @return .
   */
  @GetMapping("/wrong")
  public Map<String, String> wrong(@RequestParam("userId") Integer userId) {
    // 设置用户信息前查询一次ThreadLocal中的用户信息
    String before = Thread.currentThread().getName() + ":" + currentUser.get();
    currentUser.set(userId);
    // 设置用户信息之后再查询一次ThreadLocal中的用户信息
    String after = Thread.currentThread().getName() + ":" + currentUser.get();
    // 汇总输出两次查询结果
    Map<String, String> result = new HashMap<>();
    result.put("before", before);
    result.put("after", after);
    return result;
  }

  /**
   * http://localhost:45678/thread-local/wrong?userId=222.
   *
   * @param userId .
   * @return .
   */
  @GetMapping("/right")
  public Map<String, String> right(@RequestParam("userId") Integer userId) {
    // 设置用户信息前查询一次ThreadLocal中的用户信息
    String before = Thread.currentThread().getName() + ":" + currentUser.get();
    currentUser.set(userId);
    try {
      // 设置用户信息之后再查询一次ThreadLocal中的用户信息
      String after = Thread.currentThread().getName() + ":" + currentUser.get();
      // 汇总输出两次查询结果
      Map<String, String> result = new HashMap<>();
      result.put("before", before);
      result.put("after", after);
      return result;
    } finally {
      currentUser.remove();
    }
  }
}
