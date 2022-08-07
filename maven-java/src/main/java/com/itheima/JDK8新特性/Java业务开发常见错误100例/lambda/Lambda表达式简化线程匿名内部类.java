package com.itheima.JDK8新特性.Java业务开发常见错误100例.lambda;

/**
 * @author {胡磊}
 * @since 2022/8/5 15:48
 */
public class Lambda表达式简化线程匿名内部类 {

  public static void main(String[] args) {
    System.out.println(Thread.currentThread().getName() + "线程启动了！");
    new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName() + "线程启动了！");
      }
    }).start();
    //通过Lambda表达式简化
    new Thread(() -> System.out.println(Thread.currentThread().getName() + "线程启动了！")).start();
  }
}
