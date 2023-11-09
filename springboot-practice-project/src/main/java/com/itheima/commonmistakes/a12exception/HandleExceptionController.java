package com.itheima.commonmistakes.a12exception;

import com.itheima.commonmistakes.exceptionhandler.BusinessException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异常处理不合理示例.
 *
 * @author 胡磊
 * @since 2023/11/8 22:20
 */
@Slf4j
@RestController
@RequestMapping("/handle-exception")
public class HandleExceptionController {

  /**
   * http://localhost:45678/handle-exception/exception.
   *
   * @param b .
   */
  @GetMapping("/exception")
  public void exception(@RequestParam("business") boolean b) {
    if (b) {
      throw new BusinessException("订单不存在", 2001);
    }
    throw new RuntimeException("系统错误");
  }

  /**
   * http://localhost:45678/handle-exception/wrong1.
   */
  @GetMapping("/wrong1")
  public void wrong1() {
    try {
      readFile();
    } catch (IOException e) {
      throw new RuntimeException("系统忙请稍后再试");
    }
  }

  /**
   * http://localhost:45678/handle-exception/wrong2.
   */
  @GetMapping("/wrong2")
  public void wrong2() {
    try {
      readFile();
    } catch (IOException e) {
      log.error("文件读取错误, {}", e.getMessage());
      throw new RuntimeException("系统忙请稍后再试");
    }
  }

  /**
   * http://localhost:45678/handle-exception/wrong3.
   *
   * @param orderId .
   */
  @GetMapping("/wrong3")
  public void wrong3(@RequestParam("orderId") String orderId) {
    try {
      readFile();
    } catch (Exception e) {
      log.error("文件读取错误", e);
      throw new RuntimeException();
    }
  }

  /**
   * http://localhost:45678/handle-exception/right1.
   */
  @GetMapping("/right1")
  public void right1() {
    try {
      readFile();
    } catch (IOException e) {
      log.error("文件读取错误", e);
      throw new RuntimeException("系统忙请稍后再试");
    }
  }

  /**
   * http://localhost:45678/handle-exception/right2.
   */
  @GetMapping("/right2")
  public void right2() {
    try {
      readFile();
    } catch (IOException e) {
      throw new RuntimeException("系统忙请稍后再试", e);
    }
  }

  private void readFile() throws IOException {
    Files.readAllLines(Paths.get("a_file"));
  }
}
