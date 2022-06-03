package com.itheima.algorithms.剑指offer.动态规划.JZ42连续子数组的最大和;

/**
 * 输入一个长度为n的整型数组array，数组中的一个或连续多个整数组成一个子数组，子数组最小长度为1。
 * 求所有子数组的和的最大值。
 */
public class Solution {
    //动态规划思想，dp[i]表示以i结尾的连续子数组的最大和
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int[] dp = new int[array.length];
        dp[0] = array[0];
        int res = array[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Math.max(array[i], dp[i - 1] + array[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    //优化版本1，由于dp[i]只用到了dp[i-1]，所以只保留dp[i-1]即可
    public int FindGreatestSumOfSubArray2(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int sum = array[0];
        int res = array[0];
        for (int i = 1; i < array.length; i++) {
            sum = Math.max(sum + array[i], array[i]);
            res = Math.max(res, sum);
        }
        return res;
    }
}
