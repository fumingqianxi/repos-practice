package com.itheima;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

public class DemoTest {
  public static void main(String[] args) throws ParseException {
    //        byte[] input = new byte[] { (byte) 0xe4, (byte) 0xb8, (byte) 0xad };
    //        String b64encoded = Base64.getEncoder().encodeToString(input);
    //        System.out.println(b64encoded);
    //        System.out.println("===================");
    //        byte[] output = Base64.getDecoder().decode("5Lit");
    //        System.out.println(Arrays.toString(output)); // [-28, -72, -83]
    //        System.out.println(Integer.toHexString("hello".hashCode()));

    Calendar calendar = Calendar.getInstance();
    //        calendar.set(2022, Calendar.DECEMBER, 29, 3, 0);
    int day = calendar.get(Calendar.DATE);
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    System.out.println("day: " + day);
    System.out.println("hour: " + hour);

    Date date = new Date();
    System.out.println(date.getYear());
    System.out.println(date.getDate());
    System.out.println(date.getHours());
    System.out.println(date.toString());
    System.out.println(date.toGMTString());
    System.out.println(date.toLocaleString());
  }
}
