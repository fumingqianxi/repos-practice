package com.itheima.springinaction.装配bean.java代码装配bean.soundsystem;

import com.itheima.springinaction.装配bean.model.CDPlayer;
import com.itheima.springinaction.装配bean.model.CompactDisc;
import com.itheima.springinaction.装配bean.model.MediaPlayer;
import com.itheima.springinaction.装配bean.model.SgtPeppers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 胡磊
 * @since 2023/4/24 16:58
 */
@Configuration
public class CDPlayerConfig {

  @Bean
  public CompactDisc sgtPeppers() {
    return new SgtPeppers();
  }

  @Bean
  public MediaPlayer cdPlayer(CompactDisc compactDisc) {
    CDPlayer cdPlayer = new CDPlayer();
    cdPlayer.setCd(compactDisc);
    return cdPlayer;
  }
}
