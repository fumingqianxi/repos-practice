package com.itheima.spring实战.jdbc.domain;

import java.util.Date;

public class JdbcSpittle {
  private final Long id;
  private final JdbcSpitter spitter;
  private final String message;
  private final Date postedTime;

  public JdbcSpittle(Long id, JdbcSpitter spitter, String message, Date postedTime) {
    this.id = id;
    this.spitter = spitter;
    this.message = message;
    this.postedTime = postedTime;
  }
  
  public Long getId() {
    return this.id;
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public Date getPostedTime() {
    return this.postedTime;
  }

  public JdbcSpitter getSpitter() {
    return this.spitter;
  }

}
