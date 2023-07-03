package com.itheima.springinaction.AOP.基于注解的AOP;

import com.itheima.springinaction.AOP.model.Performance;
import org.springframework.stereotype.Component;

/**
 * @author 胡磊
 * @since 2023/4/27 19:21
 */
@Component
public class GirlPerformance implements Performance {
  @Override
  public void perform() {
    System.out.println("表演中");
//    throw new RuntimeException();
  }
}
