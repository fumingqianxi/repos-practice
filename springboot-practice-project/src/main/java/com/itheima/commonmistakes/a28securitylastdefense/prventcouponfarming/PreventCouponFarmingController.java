package com.itheima.commonmistakes.a28securitylastdefense.prventcouponfarming;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.stream.IntStream;

/**
 * 虚拟资产并不能凭空产生无限使用示例.
 *
 * @author 胡磊
 * @since 2024/2/11 13:48
 */
@Slf4j
@RequestMapping("prventcouponfarming")
@RestController
public class PreventCouponFarmingController {
  @GetMapping("wrong")
  public int wrong() {
    CouponCenter couponCenter = new CouponCenter();
    IntStream.rangeClosed(1, 10000).forEach(i -> {
      Coupon coupon = couponCenter.generateCouponWrong(1L, new BigDecimal("100"));
      couponCenter.sendCoupon(coupon);
    });
    return couponCenter.getTotalSentCoupon();
  }

  @GetMapping("right")
  public int right() {
    CouponCenter couponCenter = new CouponCenter();
    CouponBatch couponBatch = couponCenter.generateCouponBatch();
    IntStream.rangeClosed(1, 10000).forEach(i -> {
      Coupon coupon = couponCenter.generateCouponRight(1L, couponBatch);
      couponCenter.sendCoupon(coupon);
    });
    return couponCenter.getTotalSentCoupon();
  }
}
