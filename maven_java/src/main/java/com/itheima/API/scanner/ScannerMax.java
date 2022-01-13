package com.itheima.API.scanner;

import java.util.Scanner;

/**
 * 题目：
 * 键盘输入三个int数组，然后求出其中的最大值。
 */
public class ScannerMax {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入第一个数字：");
        int a = sc.nextInt();
        System.out.println("请输入第二个数字：");
        int b = sc.nextInt();
        System.out.println("请输入第三个数字：");
        int c = sc.nextInt();
        int temp = a > b ? a : b;
        int max = temp > c ? temp : c;
        System.out.println("最大数字为：" + max);
    }
}
