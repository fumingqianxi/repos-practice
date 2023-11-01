package com.itheima.annotation.黑马教程;

/**
 * JDK中预定义的一些注解
 * 		* @Override	：检测被该注解标注的方法是否是继承自父类(接口)的
 * 		* @Deprecated：该注解标注的内容，表示已过时
 * 		* @SuppressWarnings：压制警告
 *
 *
 */
@SuppressWarnings("all")
public class AnnoDemo2 {

  @MyAnno(value = 123, per = Person.P1, anno2 = @MyAnno2(show = "test"))
  public String toString() {
    return super.toString();
  }

  @Deprecated
  public void show1() {
    // 有缺陷
  }

  public void show2() {
    // 替代show1方法
  }

  public void demo() {
    show1();
  }
}
