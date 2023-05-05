package com.itheima.spring实战.装配bean.自动化装配bean.soundsystem;

import static org.junit.Assert.*;

import com.itheima.spring实战.装配bean.model.CompactDisc;
import com.itheima.spring实战.装配bean.model.MediaPlayer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 胡磊
 * @since 2023/4/24 17:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/*/*.xml")
//@ContextConfiguration(classes = CDPlayerConfig.class)
public class CDPlayerTest {
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
