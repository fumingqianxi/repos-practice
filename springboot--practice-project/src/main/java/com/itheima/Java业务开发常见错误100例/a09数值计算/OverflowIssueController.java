package com.itheima.Java业务开发常见错误100例.a09数值计算;

import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 内存溢出问题示例.
 *
 * @author 胡磊
 * @since 2023/11/6 13:20
 */
@Slf4j
@RestController
@RequestMapping("/overflow-issue")
public class OverflowIssueController {

  /** http://localhost:45678/overflow-issue/wrong. */
  @GetMapping("/wrong")
  public void wrong1() {
    long l = Long.MAX_VALUE;
    System.out.println(l + 1);
    System.out.println(l + 1 == Long.MIN_VALUE);
  }

  /** http://localhost:45678/overflow-issue/right1. */
  @GetMapping("/right1")
  public void right() {
    try {
      long l = Long.MAX_VALUE;
      System.out.println(Math.addExact(l, 1));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /** http://localhost:45678/overflow-issue/right2. */
  @GetMapping("/right2")
  public void set() {
    BigInteger i = new BigInteger(String.valueOf(Long.MAX_VALUE));
    System.out.println(i.add(BigInteger.ONE));

    try {
      long l = i.add(BigInteger.ONE).longValueExact();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
