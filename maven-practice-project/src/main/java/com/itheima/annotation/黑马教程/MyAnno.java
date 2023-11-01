package com.itheima.annotation.黑马教程;

public @interface MyAnno {

  public abstract int value();
  public abstract String show() default "张三";
  public abstract Person per();
  public abstract MyAnno2 anno2();
  public abstract String[] names() default {"aaa", "bbbb"};
}
