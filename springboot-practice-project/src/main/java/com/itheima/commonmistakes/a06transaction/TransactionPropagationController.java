package com.itheima.commonmistakes.a06transaction;

import com.itheima.commonmistakes.a06transaction.service.TransactionPropagationUserService;
import com.itheima.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 事务传播配置错误示例.
 *
 * @author 胡磊
 * @since 2023/11/5 11:16
 */
@Slf4j
@RestController
@RequestMapping("/transaction-propagation")
public class TransactionPropagationController {

  @Autowired private TransactionPropagationUserService userService;

  /**
   * http://localhost:45678/transaction-propagation/wrong.
   *
   * @param name .
   * @return .
   */
  @GetMapping("/wrong")
  public int wrong(@RequestParam("name") String name) {
    try {
      userService.createUserWrong(new UserEntity(name));
    } catch (Exception ex) {
      log.error("createUserWrong failed, reason:{}", ex.getMessage());
    }
    return userService.getUserCount(name);
  }

  /**
   * http://localhost:45678/transaction-propagation/wrong2.
   *
   * @param name .
   * @return .
   */
  @GetMapping("/wrong2")
  public int wrong2(@RequestParam("name") String name) {
    try {
      userService.createUserWrong2(new UserEntity(name));
    } catch (Exception ex) {
      log.error("createUserWrong2 failed, reason:{}", ex.getMessage(), ex);
    }
    return userService.getUserCount(name);
  }

  /**
   * http://localhost:45678/transaction-propagation/right.
   *
   * @param name .
   * @return .
   */
  @GetMapping("/right")
  public int right(@RequestParam("name") String name) {
    userService.createUserRight(new UserEntity(name));
    return userService.getUserCount(name);
  }
}
