package com.itheima.commonmistakes.a27clientdata.trustclientcalculation;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {
  private long itemId;
  private BigDecimal itemPrice;
  private int quantity;
  private BigDecimal itemTotalPrice;
}
