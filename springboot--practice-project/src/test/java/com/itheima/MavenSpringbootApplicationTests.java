package com.itheima;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MavenSpringbootApplicationTests {

  @Test
  void contextLoads() {
    String str = IntStream.rangeClosed(1, 100).mapToObj(__ -> "a").collect(Collectors.joining(""));
    String str2 = IntStream.rangeClosed(1, 100).mapToObj(i -> "a").collect(Collectors.joining(""));
    System.out.println(str);
    System.out.println(str2);
  }
}
