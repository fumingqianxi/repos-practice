package com.itheima.springinaction.AOP.基于注解的AOP;

import com.itheima.springinaction.AOP.基于注解的AOP.aspect.Audience;
import com.itheima.springinaction.AOP.基于注解的AOP.aspect.AudienceAround;
import com.itheima.springinaction.AOP.基于注解的AOP.aspect.AudienceAroundLog;
import com.itheima.springinaction.AOP.基于注解的AOP.aspect.EncoreableIntroducer;
import com.itheima.springinaction.AOP.基于注解的AOP.aspect.TrackCounter;
import com.itheima.springinaction.装配bean.model.BlankDisc;
import com.itheima.springinaction.装配bean.model.CompactDisc;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author 胡磊
 * @since 2023/4/27 19:09
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.itheima.springinaction.AOP")
public class ConcertConfig {

//  @Bean
  public Audience audience() {
    return new Audience();
  }

  @Bean
  public AudienceAround audienceAround() {
    return new AudienceAround();
  }

  @Bean
  public AudienceAroundLog audienceAroundLog() {
    return new AudienceAroundLog();
  }

  @Bean
  public TrackCounter trackCounter() {
    return new TrackCounter();
  }

  @Bean
  public CompactDisc compactDisc() {
    List<String> tracks = new ArrayList<>();
    tracks.add("track1");
    tracks.add("track2");
    tracks.add("track3");
    tracks.add("track4");
    tracks.add("track5");
    BlankDisc blankDisc = new BlankDisc("艺术", "周杰伦", tracks);
    return blankDisc;
  }

  @Bean
  public EncoreableIntroducer encoreableIntroducer() {
    return new EncoreableIntroducer();
  }
}
