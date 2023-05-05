package com.itheima.spring实战.装配bean.model;

import java.util.List;
public class BlankDisc implements CompactDisc {

  private String title;
  private String artist;
  private List<String> tracks;

  public BlankDisc(String title, String artist, List<String> tracks) {
    this.title = title;
    this.artist = artist;
    this.tracks = tracks;
  }

  public void play() {
    System.out.println("Playing " + title + " by " + artist);
    for (String track : tracks) {
      System.out.println("-Track: " + track);
    }
  }

  public void playTrack(int trackNum) {
    System.out.println("Playing " + title + "【"+tracks.get(trackNum-1)+"】 by " + artist);
  }
}