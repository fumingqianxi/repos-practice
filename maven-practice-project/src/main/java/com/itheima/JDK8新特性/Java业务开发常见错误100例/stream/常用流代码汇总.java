package com.itheima.JDK8新特性.Java业务开发常见错误100例.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

public class 常用流代码汇总 {

  @Test
  public void test1() {
      List<String> list1 = new ArrayList<>(Arrays.asList("a", "b", "c"));
      List<String> list2 = new ArrayList<>(Arrays.asList("1", "b", "c", "d"));
      List<String> result = list2.stream().filter(s -> !list1.contains(s)).collect(Collectors.toList());
      System.out.println(result);
  }
}
