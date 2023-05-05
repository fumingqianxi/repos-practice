package com.itheima.spring实战.装配bean.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CDPlayer implements MediaPlayer {

  @Autowired
  private CompactDisc cd;

  public void play() {
    cd.play();
  }
}
