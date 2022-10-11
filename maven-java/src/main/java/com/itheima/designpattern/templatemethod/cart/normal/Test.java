package com.itheima.designpattern.templatemethod.cart.normal;

import java.util.HashMap;

/**
 * @author 胡磊
 * @since 2022/6/6 22:26
 */
public class Test {

  public static void main(String[] args) {

  }

  public Cart wrong(int userId) {
    String userCategory = userId + "";
    if (userCategory.equals("Normal")) {
      NormalUserCart normalUserCart = new NormalUserCart();
      return normalUserCart.process(userId, new HashMap<>());
    }
    if (userCategory.equals("Vip")) {
      VipUserCart vipUserCart = new VipUserCart();
      return vipUserCart.process(userId, new HashMap<>());
    }
    if (userCategory.equals("Internal")) {
      InternalUserCart internalUserCart = new InternalUserCart();
      return internalUserCart.process(userId, new HashMap<>());
    }
    return null;
  }
}
