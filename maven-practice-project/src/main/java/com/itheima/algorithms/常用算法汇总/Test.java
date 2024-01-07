package com.itheima.algorithms.常用算法汇总;

import com.itheima.algorithms.常用算法汇总.list分批调用.Solution;
import java.util.ArrayList;
import java.util.List;

public class Test {

  public static void main(String[] args) {
    List<String> userIds = new ArrayList<>();
    for (int i = 0; i < 1001; i++) {
      userIds.add(i + 1 + "");
    }
    Solution.batchCall(userIds, 500);
    System.out.println("==========================");
    List<List<String>> lists = Solution.fixedGrouping(userIds, 500);
    System.out.println(lists.size());
  }
}
