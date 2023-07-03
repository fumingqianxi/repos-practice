package com.itheima.springinaction.AOP.基于注解的AOP.aspect;

import java.util.HashMap;
import java.util.Map;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author 胡磊
 * @since 2023/4/28 13:34
 */
@Aspect
public class TrackCounter {

  private Map<Integer, Integer> trackCounts = new HashMap<>();

  @Pointcut("execution(* com.itheima.springinaction.装配bean.model.CompactDisc.playTrack(int)) && args(trackNumber)")
  public void trackPlayed(int trackNumber) {}

  @Before("trackPlayed(num)")
  public void countTrack(int num) {
    int currentCount = getPlayCount(num);
    trackCounts.put(num, currentCount + 1);
  }

  public int getPlayCount(int trackNumber) {
    return trackCounts.containsKey(trackNumber) ? trackCounts.get(trackNumber) : 0;
  }
}
