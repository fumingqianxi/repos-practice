package com.itheima.algorithms.常用算法汇总.list分批调用;

import com.itheima.util.CheckEmptyUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 分批调用功能实现类.
 *
 * @author 胡磊
 * @since 2024/1/7 14:22
 */
public class Solution {

  public static void batchCall(List<String> userIds, int batchSize) {
    int totalCount = userIds.size();
    int batchCount = (totalCount + batchSize - 1) / batchSize;
    for (int i = 0; i < batchCount; i++) {
      int fromIndex = i * batchSize;
      int toIndex = Math.min((i + 1) * batchSize, totalCount);
      List<String> batchList = new ArrayList<>(userIds.subList(fromIndex, toIndex));
      System.out.println(batchList);
    }
  }

  public static <T> List<List<T>> fixedGrouping(List<T> source, int batchSize) {
    if (CheckEmptyUtil.isEmpty(source) || batchSize <= 0) {
      return Collections.emptyList();
    }
    List<List<T>> result = new ArrayList<>();
    int totalCount = source.size();
    int batchCount = (totalCount + batchSize - 1) / batchSize;
    for (int i = 0; i < batchCount; i++) {
      int fromIndex = i * batchSize;
      int toIndex = Math.min((i + 1) * batchSize, totalCount);
      List<T> batchList = new ArrayList<>(source.subList(fromIndex, toIndex));
      result.add(batchList);
    }
    return result;
  }
}
