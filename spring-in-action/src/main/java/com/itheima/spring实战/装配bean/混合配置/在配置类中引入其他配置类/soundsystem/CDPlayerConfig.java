package com.itheima.spring实战.装配bean.混合配置.在配置类中引入其他配置类.soundsystem;
import com.itheima.spring实战.装配bean.model.CDPlayer;
import com.itheima.spring实战.装配bean.model.CompactDisc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CDPlayerConfig {
  
  @Bean
  public CDPlayer cdPlayer(CompactDisc compactDisc) {
    return new CDPlayer(compactDisc);
  }
}
