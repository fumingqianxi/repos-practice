package com.itheima;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoTest {

  public static void main(String[] args) {
    String[] split = "a b c".split("\\s");// { "a", "b", "c" }
    // 输出split的每个元素
    Arrays.stream(split).forEach(System.out::println);
    String[] split1 = "a b  c".split("\\s");// { "a", "b", "", "c" }
    // 输出split1的每个元素
    Arrays.stream(split1).forEach(System.out::println);
    String[] split2 = "a, b ;; c".split("[,;\\s]+");// { "a", "b", "c" }
    // 输出split2的每个元素
    Arrays.stream(split2).forEach(System.out::println);
  }
}
