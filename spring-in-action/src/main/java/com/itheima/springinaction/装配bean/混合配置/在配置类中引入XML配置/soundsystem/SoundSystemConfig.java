package com.itheima.springinaction.装配bean.混合配置.在配置类中引入XML配置.soundsystem;

import com.itheima.springinaction.装配bean.混合配置.在配置类中引入其他配置类.soundsystem.CDPlayerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import(CDPlayerConfig.class)
@ImportResource("classpath:混合配置/cd-config.xml")
public class SoundSystemConfig {
}
