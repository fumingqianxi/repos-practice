package com.itheima.commonmistakes.a28securitylastdefense.prventcouponfarming;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Coupon {
  private long userId;
  private BigDecimal amount;
}
