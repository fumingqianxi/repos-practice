package com.itheima.commonmistakes.a12exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 将异常定义为静态变量问题示例.
 *
 * @author 胡磊
 * @since 2023/11/9 9:38
 */
@Slf4j
@RestController
@RequestMapping("/predefined-exception")
public class PredefinedExceptionController {

  /**
   * http://localhost:45678/predefined-exception/wrong.
   */
  @GetMapping("/wrong")
  public void wrong() {
    try {
      createOrderWrong();
    } catch (Exception ex) {
      log.error("createOrder got error", ex);
    }
    try {
      cancelOrderWrong();
    } catch (Exception ex) {
      log.error("cancelOrder got error", ex);
    }
  }

  /**
   * http://localhost:45678/predefined-exception/right.
   */
  @GetMapping("/right")
  public void right() {
    try {
      createOrderRight();
    } catch (Exception ex) {
      log.error("createOrder got error", ex);
    }
    try {
      cancelOrderRight();
    } catch (Exception ex) {
      log.error("cancelOrder got error", ex);
    }
  }

  private void createOrderWrong() {
    throw Exceptions.ORDEREXISTS;
  }

  private void cancelOrderWrong() {
    throw Exceptions.ORDEREXISTS;
  }

  private void createOrderRight() {
    throw Exceptions.orderExists();
  }

  private void cancelOrderRight() {
    throw Exceptions.orderExists();
  }
}
