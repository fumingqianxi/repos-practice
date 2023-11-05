package com.itheima.Java业务开发常见错误100例.a06事务;

import com.itheima.Java业务开发常见错误100例.a06事务.service.TransactionProxyFailedUserService;
import com.itheima.Java业务开发常见错误100例.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 事务不生效问题示例.
 *
 * @author 胡磊
 * @since 2023/11/4 16:16
 */
@Slf4j
@RestController
@RequestMapping("/transaction-proxy-failed")
public class TransactionProxyFailedController {

  @Autowired private TransactionProxyFailedUserService userService;

  /**
   * http://localhost:45678/transaction-proxy-failed/wrong1.
   *
   * @param name .
   * @return .
   */
  @GetMapping("/wrong1")
  public int wrong1(@RequestParam("name") String name) {
    return userService.createUserWrong1(name);
  }

  /**
   * http://localhost:45678/transaction-proxy-failed/wrong2.
   *
   * @param name .
   * @return .
   */
  @GetMapping("/wrong2")
  public int wrong2(@RequestParam("name") String name) {
    return userService.createUserWrong2(name);
  }

  /**
   * http://localhost:45678/transaction-proxy-failed/right1.
   *
   * @param name .
   * @return .
   */
  @GetMapping("/right1")
  public int right1(@RequestParam("name") String name) {
    return userService.createUserRight(name);
  }

  /**
   * http://localhost:45678/transaction-proxy-failed/right2.
   *
   * @param name .
   * @return .
   */
  @GetMapping("/right2")
  public int right2(@RequestParam("name") String name) {
    try {
      userService.createUserPublic(new UserEntity(name));
    } catch (Exception ex) {
      log.error("create user failed because {}", ex.getMessage());
    }
    return userService.getUserCount(name);
  }
}
