package com.itheima.annotation.黑马教程;

@MyAnno(value = 123, per = Person.P1, anno2 = @MyAnno2(show = "test"), names = {"ddd", "eeee"})
@MyAnno3
public class Worker {
  @MyAnno3
  public String name = "aaa";

  @MyAnno3
  public void show() {

  }
}
