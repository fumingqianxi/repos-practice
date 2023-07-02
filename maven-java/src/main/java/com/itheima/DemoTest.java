package com.itheima;

import java.util.Date;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoTest {

  public static void main(String[] args) throws Exception {
    Date date = new Date();
    System.out.println(date.getTime());
    Date date1 = new Date(1687968000L * 1000L);
    System.out.println(date1);
  }
}
