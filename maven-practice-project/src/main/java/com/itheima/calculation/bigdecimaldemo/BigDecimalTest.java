package com.itheima.calculation.bigdecimaldemo;

import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * BigDecimal测试.
 *
 * @author 胡磊
 * @since 2024/3/4 14:12
 */
@Slf4j
public class BigDecimalTest {

  /** 如果直接传double构造BigDecimal表现还不如直接使用double，因为double正常时，BigDecimal也异常了. */
  @Test
  public void test01() {
    System.out.println("double异常时，使用BigDecimal：");
    System.out.println(new BigDecimal(0.1).add(new BigDecimal(0.2)));
    System.out.println(new BigDecimal(1.0).subtract(new BigDecimal(0.8)));
    System.out.println(new BigDecimal(4.015).multiply(new BigDecimal(100)));
    System.out.println(new BigDecimal(123.3).divide(new BigDecimal(100)));
    System.out.println("===============分隔线===============");
    System.out.println("double正常时，使用BigDecimal：");
    System.out.println(new BigDecimal(0.1).add(new BigDecimal(0.3)));
    System.out.println(new BigDecimal(1.0).subtract(new BigDecimal(0.4)));
    System.out.println(new BigDecimal(4.015).multiply(new BigDecimal(10)));
    System.out.println(new BigDecimal(123.3).divide(new BigDecimal(10)));
  }

  /** 使用字符串的构造BigDecimal是正确做法. */
  @Test
  public void test02() {
    System.out.println(new BigDecimal("0.1").add(new BigDecimal("0.2")));
    System.out.println(new BigDecimal("1.0").subtract(new BigDecimal("0.8")));
    System.out.println(new BigDecimal("4.015").multiply(new BigDecimal("100")));
    System.out.println(new BigDecimal("123.3").divide(new BigDecimal("100")));
  }

  /** 测试下小数点位数问题，可以看到积的小数点位数是两个乘数的位数相加. */
  @Test
  public void test03() {
    System.out.println(new BigDecimal("4.015").multiply(new BigDecimal("100")));
    System.out.println(new BigDecimal("4.015").multiply(new BigDecimal("100.0")));
    // Double.toString(100)其实是100.0
    System.out.println(new BigDecimal("4.015").multiply(new BigDecimal(Double.toString(100))));
    System.out.println(new BigDecimal("4.0150").multiply(new BigDecimal("100.00")));
  }

  /** 测试下精度和位数，不同方式得到的BigDecimal，精度和位数不一样. */
  @Test
  public void testScale() {
    BigDecimal bigDecimal1 = new BigDecimal("100");
    BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(100d));
    BigDecimal bigDecimal3 = new BigDecimal(String.valueOf(100));
    BigDecimal bigDecimal4 = BigDecimal.valueOf(100d);
    BigDecimal bigDecimal5 = new BigDecimal(Double.toString(100));

    // scale 0 precision 3 result 401.500
    print(bigDecimal1);
    // scale 1 precision 4 result 401.5000
    print(bigDecimal2);
    // scale 0 precision 3 result 401.500
    print(bigDecimal3);
    // scale 1 precision 4 result 401.5000
    print(bigDecimal4);
    // scale 1 precision 4 result 401.5000
    print(bigDecimal5);
  }

  /** 测试比较大小. */
  @Test
  public void testCompare() {
    BigDecimal a = new BigDecimal("10.10");
    BigDecimal b = new BigDecimal("10.11111");
    if (a.compareTo(b) == 0) {
      System.out.println("a = b");
    }
    if (a.compareTo(b) < 0) {
      System.out.println("a < b");
    }
    if (a.compareTo(b) > 0) {
      System.out.println("a > b");
    }
    if (a.compareTo(b) != 0) {
      System.out.println("a != b");
    }
    if (a.compareTo(b) >= 0) {
      System.out.println("a >= b");
    }
    if (a.compareTo(b) <= 0) {
      System.out.println("a <= b");
    }
  }

  private static void print(BigDecimal bigDecimal) {
    log.info(
        "scale {} precision {} result {}",
        bigDecimal.scale(),
        bigDecimal.precision(),
        bigDecimal.multiply(new BigDecimal("4.015")));
  }
}
