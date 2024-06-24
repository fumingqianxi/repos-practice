package com.itheima.JDK8新特性.Java业务开发常见错误100例.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

public class 常用流操作汇总 {

  /**
   * 使用流过滤掉指定元素.
   */
  @Test
  public void test1() {
    List<String> list1 = new ArrayList<>(Arrays.asList("a", "b", "c"));
    List<String> list2 = new ArrayList<>(Arrays.asList("1", "b", "c", "d"));
    List<String> result =
        list2.stream().filter(s -> !list1.contains(s)).collect(Collectors.toList());
    System.out.println(result);
  }

  /**
   * 使用流排序.
   */
  @Test
  public void test2() {
    List<String> list1 = new ArrayList<>(Arrays.asList("1", "3", "4", "5", "2"));
    List<String> result = list1.stream().sorted().collect(Collectors.toList());
    System.out.println(result);

    List<User> list2 = new ArrayList<>();
    list2.add(new User(30, "小明"));
    list2.add(new User(19, "小王"));
    list2.add(new User(50, "小李"));
    // 按对象属性排序
    List<User> result2 = list2.stream().sorted(Comparator.comparing(User::getAge).reversed())
        .collect(Collectors.toList());
    System.out.println(result2);
  }

  /**
   * 使用流将列表中的int类型属性转为String类型列表.
   */
  @Test
  public void test3() {
    List<User> list = new ArrayList<>();
    list.add(new User(30, "小明"));
    list.add(new User(19, "小王"));
    list.add(new User(50, "小李"));
    List<String> result =
        list.stream().map(user -> user.getAge() + "").collect(Collectors.toList());
    System.out.println(result);
  }

  @Data
  @AllArgsConstructor
  private class User {
    private int age;
    private String name;

    @Override
    public String toString() {
      return "User{" +
          "age=" + age +
          ", name='" + name + '\'' +
          '}';
    }
  }
}
