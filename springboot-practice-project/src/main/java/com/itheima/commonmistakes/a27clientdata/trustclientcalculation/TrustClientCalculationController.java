package com.itheima.commonmistakes.a27clientdata.trustclientcalculation;

import com.itheima.commonmistakes.exceptionhandler.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * 客户端的计算不可信问题示例.
 *
 * @author 胡磊
 * @since 2024/2/10 14:37
 */
@Slf4j
@RequestMapping("trustclientcalculation")
@RestController
public class TrustClientCalculationController {

  @PostMapping("/order")
  public void wrong(@RequestBody Order order) {
    this.createOrder(order);
  }

  @PostMapping("/orderRight")
  public void right(@RequestBody Order order) {
    Item item = Db.getItem(order.getItemId());
    if (!order.getItemPrice().equals(item.getItemPrice())) {
      throw new BusinessException("您选购的商品价格有变化，请重新下单", -1);
    }
    order.setItemPrice(item.getItemPrice());
    BigDecimal totalPrice = item.getItemPrice().multiply(BigDecimal.valueOf(order.getQuantity()));
    if (order.getItemTotalPrice().compareTo(totalPrice) != 0) {
      throw new BusinessException("您选购的商品总价有变化，请重新下单", -1);
    }
    order.setItemTotalPrice(totalPrice);
    createOrder(order);
  }

  @PostMapping("orderRight2")
  public Order right2(@RequestBody CreateOrderRequest createOrderRequest) {
    Item item = Db.getItem(createOrderRequest.getItemId());
    Order order = new Order();
    order.setItemPrice(item.getItemPrice());
    order.setItemTotalPrice(item.getItemPrice().multiply(BigDecimal.valueOf(order.getQuantity())));
    createOrder(order);
    return order;
  }

  private void createOrder(Order order) {

  }
}
