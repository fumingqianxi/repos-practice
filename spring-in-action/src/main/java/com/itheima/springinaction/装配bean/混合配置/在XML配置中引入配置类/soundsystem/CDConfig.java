package com.itheima.springinaction.装配bean.混合配置.在XML配置中引入配置类.soundsystem;

import com.itheima.springinaction.装配bean.model.CompactDisc;
import com.itheima.springinaction.装配bean.model.SgtPeppers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CDConfig {
  @Bean
  public CompactDisc compactDisc() {
    return new SgtPeppers();
  }
}