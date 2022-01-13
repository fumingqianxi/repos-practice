package com.itheima.API.scanner;

import java.util.Scanner;

/**
 * 题目：
 * 键盘输入两个int数字，并且求出和值。
 * 思路：
 * 1、既然需要键盘输入，那么就用Scanner
 * 2、Scanner的三个使用步骤：导包、创建、使用
 */
public class ScannerSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入第一个数字：");
        int a = sc.nextInt();
        System.out.println("请输入第二个数字：");
        int b = sc.nextInt();
        int result = a + b;
        System.out.println("结果是：" + result);
    }
}
