package com.itheima.spring实战.装配bean.混合配置.在配置类中引入其他配置类.soundsystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.itheima.spring实战.装配bean.model.CompactDisc;
import com.itheima.spring实战.装配bean.model.MediaPlayer;
import com.itheima.spring实战.装配bean.混合配置.在配置类中引入其他配置类.soundsystem.SoundSystemConfig;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SoundSystemConfig.class)
public class SoundSystemConfigTest {
  @Rule
  public final StandardOutputStreamLog log = new StandardOutputStreamLog();

  @Autowired
  private CompactDisc cd;
  @Autowired
  private MediaPlayer player;

  @Test
  public void cdShouldNotBeNull() {
    assertNotNull(cd);
  }

  @Test
  public void play() {
    player.play();
    assertEquals(
        "Playing Sgt. Pepper's Lonely Hearts Club Band by The Beatles\r\n",
        log.getLog());
  }
}
