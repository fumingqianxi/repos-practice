package com.itheima.designpattern.templatemethod.cart.improvement;

import com.itheima.designpattern.templatemethod.cart.normal.Item;
import java.math.BigDecimal;

/**
 * @author 胡磊
 * @since 2022/6/6 23:04
 */
public class VipUserCart extends NormalUserCart {

  @Override
  protected void processCouponPrice(long userId, Item item) {
    if (item.getQuantity() > 2) {
      item.setCouponPrice(item.getPrice()
                              .multiply(BigDecimal.valueOf(100 - 11).divide(new BigDecimal("100")))
                              .multiply(BigDecimal.valueOf(item.getQuantity() - 2)));
    } else {
      item.setCouponPrice(BigDecimal.ZERO);
    }
  }
}
