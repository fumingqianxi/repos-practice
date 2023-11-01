package com.itheima.designpattern.templatemethod.cart.improvement;

import com.itheima.designpattern.templatemethod.cart.normal.Item;
import java.math.BigDecimal;

/**
 * @author 胡磊
 * @since 2022/6/6 23:03
 */
public class NormalUserCart extends AbstractCart {

  @Override
  protected void processCouponPrice(long userId, Item item) {
    item.setCouponPrice(BigDecimal.ZERO);
  }

  @Override
  protected void processDeliveryPrice(long userId, Item item) {
    item.setDeliveryPrice(item.getPrice()
                              .multiply(BigDecimal.valueOf(item.getQuantity()))
                              .multiply(new BigDecimal("0.1")));
  }
}
