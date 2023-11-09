package com.itheima.commonmistakes.a09numeralcalculations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 浮点数舍入和格式化问题示例.
 *
 * @author 胡磊
 * @since 2023/11/6 13:20
 */
@Slf4j
@RestController
@RequestMapping("/rounding")
public class RoundingController {

  /** http://localhost:45678/rounding/wrong1. */
  @GetMapping("/wrong1")
  public void wrong1() {
    double num1 = 3.35;
    float num2 = 3.35f;
    System.out.println(String.format("%.1f", num1)); // 四舍五入
    System.out.println(String.format("%.1f", num2));
  }

  /** http://localhost:45678/rounding/wrong2. */
  @GetMapping("/wrong2")
  public void wrong2() {
    double num1 = 3.35;
    final float num2 = 3.35f;
    DecimalFormat format = new DecimalFormat("#.##");
    format.setRoundingMode(RoundingMode.DOWN);
    System.out.println(format.format(num1));
    format.setRoundingMode(RoundingMode.DOWN);
    System.out.println(format.format(num2));
  }

  /** http://localhost:45678/rounding/right. */
  @GetMapping("/right")
  public void right() {
    BigDecimal num1 = new BigDecimal("3.35");
    BigDecimal num2 = num1.setScale(1, BigDecimal.ROUND_DOWN);
    System.out.println(num2);
    BigDecimal num3 = num1.setScale(1, BigDecimal.ROUND_HALF_UP);
    System.out.println(num3);
  }
}
