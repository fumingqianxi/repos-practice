package com.itheima.commonmistakes.a27clientdata.trustclientcalculation;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Item {
  private long itemId;
  private BigDecimal itemPrice;
}
