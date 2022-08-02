package com.itheima.designpattern.templatemethod.cart.normal;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import java.math.BigDecimal;
import lombok.Data;

/**
 * @author {胡磊}
 * @since 2022/6/6 18:28
 */
@Data
public class Item {

  private long id;

  private int quantity;

  private BigDecimal price;

  private BigDecimal couponPrice;

  private BigDecimal deliveryPrice;
}
