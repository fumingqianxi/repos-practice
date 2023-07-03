package com.itheima.springinaction.装配bean.model;

import org.springframework.stereotype.Component;

/**
 * @author 胡磊
 * @since 2023/4/24 16:54
 */
@Component
public class SgtPeppers implements CompactDisc {

  private String title = "Sgt. Pepper's Lonely Hearts Club Band";
  private String artist = "The Beatles";

  @Override
  public void play() {
    System.out.println("Playing " + title + " by " + artist);
  }

  @Override
  public void playTrack(int trackNum) {

  }
}
