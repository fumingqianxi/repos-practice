package com.itheima.commonmistakes.a16日期时间类.dateformat;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CommonMistakesApplication {

  private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private static ThreadLocal<SimpleDateFormat> threadSafeSimpleDateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

  public static void main(String[] args) throws Exception {
//    wrong2();
    wrong2fix();
  }

  public static void wrong2() throws Exception {
    ExecutorService threadPool = Executors.newFixedThreadPool(100);
    for (int i = 0; i < 20; i++) {
      threadPool.execute(
          () -> {
            for (int i1 = 0; i1 < 10; i1++) {
              try {
                System.out.println(simpleDateFormat.parse("2020-01-01 11:12:13"));
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
          });
    }
    threadPool.shutdown();
    threadPool.awaitTermination(1, TimeUnit.HOURS);
  }

  public static void wrong2fix() throws Exception {
    ExecutorService threadPool = Executors.newFixedThreadPool(100);
    for (int i = 0; i < 20; i++) {
      threadPool.execute(
          () -> {
            for (int i1 = 0; i1 < 10; i1++) {
              try {
                System.out.println(threadSafeSimpleDateFormat.get().parse("2020-01-01 11:12:13"));
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
          });
    }
    threadPool.shutdown();
    threadPool.awaitTermination(1, TimeUnit.HOURS);
  }
}
