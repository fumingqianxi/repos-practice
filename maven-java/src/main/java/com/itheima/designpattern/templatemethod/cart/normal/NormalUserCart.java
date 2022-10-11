package com.itheima.designpattern.templatemethod.cart.normal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 胡磊
 * @since 2022/6/6 20:45
 */
public class NormalUserCart {

  public Cart process(long userId, Map<Long, Integer> map) {
    Cart cart = new Cart();
    List<Item> itemList = new ArrayList<>();
    for (Map.Entry<Long, Integer> entry : map.entrySet()) {
      Item item = new Item();
      item.setId(entry.getKey());
      item.setQuantity(entry.getValue());
      item.setPrice(new BigDecimal(100));
      item.setCouponPrice(BigDecimal.ZERO);
      itemList.add(item);
    }
    cart.setItems(itemList);
    itemList.stream().forEach(item -> {
      item.setDeliveryPrice(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))
                                .multiply(new BigDecimal("0.1")));
      if (item.getQuantity() > 2) {
        item.setCouponPrice(item.getPrice().multiply(
            BigDecimal.valueOf(100 - 1).divide(new BigDecimal(
                "100"))).multiply(BigDecimal.valueOf(item.getQuantity() - 2)));
      } else {
        item.setCouponPrice(BigDecimal.ZERO);
      }
    });
    cart.setTotalItemPrice(cart.getItems().stream().map(
            item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                               .reduce(BigDecimal.ZERO, BigDecimal::add));
    cart.setTotalDiscount(cart.getItems().stream().map(Item::getCouponPrice)
                              .reduce(BigDecimal.ZERO, BigDecimal::add));
    cart.setPayPrice(cart.getTotalItemPrice().add(cart.getTotalDeliveryPrice())
                         .subtract(cart.getTotalDiscount()));
    return cart;
  }
}
