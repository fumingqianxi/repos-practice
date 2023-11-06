package com.itheima.Java业务开发常见错误100例.a09数值计算;

import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * double运算错误示例.
 *
 * @author 胡磊
 * @since 2023/11/5 13:20
 */
@Slf4j
@RestController
@RequestMapping("/dangerous-double")
public class DangerousDoubleController {

  /** http://localhost:45678/dangerous-double/wrong1. */
  @GetMapping("/wrong1")
  public void wrong1() {
    System.out.println(0.1 + 0.2);
    System.out.println(0.1);
    System.out.println(1.0 - 0.8);
    System.out.println(4.015 * 100);
    System.out.println(123.3 / 100);

    double amount1 = 2.15;
    double amount2 = 1.10;

    if (amount1 - amount2 == 1.05) {
      System.out.println("OK");
    }
  }

  /** http://localhost:45678/dangerous-double/wrong2. */
  @GetMapping("/wrong2")
  public void wrong2() {
    System.out.println(new BigDecimal(0.1).add(new BigDecimal(0.2)));
    // 正确方式
    System.out.println(BigDecimal.valueOf(0.1).add(BigDecimal.valueOf(0.2)));
    System.out.println(new BigDecimal(1.0).subtract(new BigDecimal(0.8)));
    System.out.println(new BigDecimal(4.015).multiply(new BigDecimal(100)));
    System.out.println(new BigDecimal(123.3).divide(new BigDecimal(100)));
  }

  /** http://localhost:45678/dangerous-double/right. */
  @GetMapping("/right")
  public void right() {
    System.out.println(new BigDecimal("0.1").add(new BigDecimal("0.2")));
    System.out.println(new BigDecimal("1.0").subtract(new BigDecimal("0.8")));
    System.out.println(new BigDecimal("4.015").multiply(new BigDecimal("100.0")));
    System.out.println(new BigDecimal("123.3").divide(new BigDecimal("100")));
    System.out.println("=============================");
    // 尝试下通过+操作构造字符串，与直接输入字符串没本质区别
    System.out.println(new BigDecimal(0.1 + "").add(new BigDecimal(0.2 + "")));
    System.out.println(new BigDecimal(1.0 + "").subtract(new BigDecimal(0.8 + "")));
    System.out.println(new BigDecimal(4.015 + "").multiply(new BigDecimal(100 + "")));
    System.out.println(new BigDecimal(123.3 + "").divide(new BigDecimal(100 + "")));
    System.out.println("=============================");
    // 尝试下Double.toString的方式
    System.out.println(new BigDecimal(Double.toString(0.1)).add(new BigDecimal((Double.toString(0.2)))));
    System.out.println(new BigDecimal(Double.toString(1.0)).subtract(new BigDecimal((Double.toString(0.8)))));
    System.out.println(new BigDecimal(Double.toString(4.015)).multiply(new BigDecimal((Double.toString(100)))));
    System.out.println(new BigDecimal(Double.toString(123.3)).divide(new BigDecimal((Double.toString(100)))));
  }

  /** http://localhost:45678/dangerous-double/test-scale. */
  @GetMapping("/test-scale")
  public void testScale() {
    BigDecimal bigDecimal1 = new BigDecimal("100");
    BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(100d));
    BigDecimal bigDecimal3 = new BigDecimal(String.valueOf(100));
    BigDecimal bigDecimal4 = BigDecimal.valueOf(100d);
    BigDecimal bigDecimal5 = new BigDecimal(Double.toString(100));

    print(bigDecimal1); //scale 0 precision 3 result 401.500
    print(bigDecimal2); //scale 1 precision 4 result 401.5000
    print(bigDecimal3); //scale 0 precision 3 result 401.500
    print(bigDecimal4); //scale 1 precision 4 result 401.5000
    print(bigDecimal5); //scale 1 precision 4 result 401.5000
  }

  private static void print(BigDecimal bigDecimal) {
    log.info("scale {} precision {} result {}", bigDecimal.scale(), bigDecimal.precision(), bigDecimal.multiply(new BigDecimal("4.015")));
  }
}
