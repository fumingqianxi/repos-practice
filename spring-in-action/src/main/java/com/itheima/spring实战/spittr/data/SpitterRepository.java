package com.itheima.spring实战.spittr.data;

import com.itheima.spring实战.spittr.Spitter;

public interface SpitterRepository {

  Spitter save(Spitter spitter);
  
  Spitter findByUsername(String username);

}
