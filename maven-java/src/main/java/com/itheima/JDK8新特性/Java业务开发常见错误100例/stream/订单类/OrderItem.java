package com.itheima.JDK8新特性.Java业务开发常见错误100例.stream.订单类;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author {胡磊}
 * @since 2022/8/6 21:21
 */
//订单商品类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
  private Long productId;//商品ID
  private String productName;//商品名称
  private Double productPrice;//商品价格
  private Integer productQuantity;//商品数量
}
