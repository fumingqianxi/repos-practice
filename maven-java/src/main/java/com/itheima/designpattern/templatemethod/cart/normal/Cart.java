package com.itheima.designpattern.templatemethod.cart.normal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.Data;

/**
 * @author {胡磊}
 * @since 2022/6/6 18:17
 */
@Data
public class Cart {
  private List<Item> items = new ArrayList<>();

  private BigDecimal totalDiscount;

  private BigDecimal totalItemPrice;

  private BigDecimal totalDeliveryPrice;

  private BigDecimal payPrice;
}
