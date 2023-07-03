package com.itheima.springinaction.AOP.基于XML的AOP;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 胡磊
 * @since 2023/4/28 16:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/基于XML的AOP/performance.xml")
public class PerformanceTest {

  @Autowired
  public GirlPerformance performance;

  @Test
  public void performanceTest() {
    performance.perform();
  }
}
