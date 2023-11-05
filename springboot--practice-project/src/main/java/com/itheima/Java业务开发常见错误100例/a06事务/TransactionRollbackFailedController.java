package com.itheima.Java业务开发常见错误100例.a06事务;

import com.itheima.Java业务开发常见错误100例.a06事务.service.TransactionRollbackFailedUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 事务未回滚错误示例.
 *
 * @author 胡磊
 * @since 2023/11/4 16:16
 */
@Slf4j
@RestController
@RequestMapping("transaction-rollback-failed")
public class TransactionRollbackFailedController {

  @Autowired private TransactionRollbackFailedUserService userService;

  /**
   * http://localhost:45678/transaction-rollback-failed/wrong1.
   *
   * @param name .
   * @return .
   */
  @GetMapping("wrong1")
  public int wrong1(@RequestParam("name") String name) {
    userService.createUserWrong1(name);
    return userService.getUserCount(name);
  }

  /**
   * http://localhost:45678/transaction-rollback-failed/wrong2.
   *
   * @param name .
   * @return .
   */
  @GetMapping("wrong2")
  public int wrong2(@RequestParam("name") String name) throws Exception{
    try {
      userService.createUserWrong2(name);
    } catch (Exception e) {
      log.error("create user failed", e);
    }
    return userService.getUserCount(name);
  }

  /**
   * http://localhost:45678/transaction-rollback-failed/right1.
   *
   * @param name .
   * @return .
   */
  @GetMapping("right1")
  public int right1(@RequestParam("name") String name) {
    try {
      userService.createUserRight1(name);
    } catch (Exception e) {
      log.error("create user failed", e);
    }
    return userService.getUserCount(name);
  }

  /**
   * http://localhost:45678/transaction-rollback-failed/right2.
   *
   * @param name .
   * @return .
   */
  @GetMapping("right2")
  public int right2(@RequestParam("name") String name) {
    try {
      userService.createUserRight2(name);
    } catch (Exception e) {
      log.error("create user failed", e);
    }
    return userService.getUserCount(name);
  }
}
