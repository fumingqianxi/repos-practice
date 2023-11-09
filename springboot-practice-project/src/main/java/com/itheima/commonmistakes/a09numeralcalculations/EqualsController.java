package com.itheima.commonmistakes.a09numeralcalculations;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BigDecimal判等问题示例.
 *
 * @author 胡磊
 * @since 2023/11/6 13:20
 */
@Slf4j
@RestController
@RequestMapping("/equals")
public class EqualsController {

  /** http://localhost:45678/equals/wrong1. */
  @GetMapping("/wrong1")
  public void wrong1() {
    System.out.println(new BigDecimal("1.0").equals(new BigDecimal("1")));
  }

  /** http://localhost:45678/equals/right. */
  @GetMapping("/right")
  public void right() {
    System.out.println(new BigDecimal("1.0").compareTo(new BigDecimal("1")) == 0);
  }

  /** http://localhost:45678/equals/set. */
  @GetMapping("/set")
  public void set() {
    Set<BigDecimal> hashSet1 = new HashSet<>();
    hashSet1.add(new BigDecimal("1.0"));
    System.out.println(hashSet1.contains(new BigDecimal("1"))); // 返回false

    Set<BigDecimal> hashSet2 = new HashSet<>();
    hashSet2.add(new BigDecimal("1.0").stripTrailingZeros());
    System.out.println(hashSet2.contains(new BigDecimal("1.000").stripTrailingZeros())); // 返回true

    Set<BigDecimal> treeSet = new TreeSet<>();
    treeSet.add(new BigDecimal("1.0"));
    System.out.println(treeSet.contains(new BigDecimal("1"))); // 返回true
  }
}
