package com.itheima.spring实战.AOP.基于注解的AOP;

import static org.junit.Assert.*;

import com.itheima.spring实战.AOP.model.Performance;
import com.itheima.spring实战.AOP.基于注解的AOP.aspect.TrackCounter;
import com.itheima.spring实战.AOP.基于注解的AOP.通过注解AOP添加方法.Encoreable;
import com.itheima.spring实战.装配bean.model.CompactDisc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 胡磊
 * @since 2023/4/27 19:18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConcertConfig.class)
public class AnnotationAOPTest {

  @Autowired
  public Performance girlPerformance;

  @Autowired
  public CompactDisc cd;

  @Autowired
  private TrackCounter trackCounter;

  @Test
  public void performanceTest() {
    girlPerformance.perform();
  }

  @Test
  public void performanceAroundTest() {
    girlPerformance.perform();
  }

  @Test
  public void TrackCounterTest() {
    cd.playTrack(1);
    cd.playTrack(3);
    cd.playTrack(3);
    cd.playTrack(3);
    cd.playTrack(4);
    cd.playTrack(4);

    assertEquals(1, trackCounter.getPlayCount(1));
    assertEquals(0, trackCounter.getPlayCount(2));
    assertEquals(3, trackCounter.getPlayCount(3));
    assertEquals(2, trackCounter.getPlayCount(4));
  }

  @Test
  public void EncoreableTest() {
    girlPerformance.perform();
    //由于performance引入了Encoreable接口（指定了实现类），所以能强转为该接口
    Encoreable encoreable = (Encoreable) girlPerformance;
    encoreable.performEncore();
  }
}
