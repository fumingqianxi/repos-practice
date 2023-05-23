package com.itheima.spring实战.spittr.web;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class SpittleForm implements Serializable {

//  @NotNull
//  @Size(min=1, max=140)
  private String message;
  
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "SpittleForm{" +
        "message='" + message + '\'' +
        '}';
  }
}
