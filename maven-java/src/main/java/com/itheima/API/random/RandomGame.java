package com.itheima.API.random;

import java.util.Random;
import java.util.Scanner;

public class RandomGame {
    public static void main(String[] args) {
        Random rm = new Random();
        int num = rm.nextInt(100);
        System.out.println(num);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入要猜的数字：");
            int k = sc.nextInt();
            if (k == num) {
                System.out.println("恭喜你，猜对了！");
                break;
            } else if (k > num) {
                System.out.println("猜大了！");
            }
            if (k < num) {
                System.out.println("猜小了！");
            }
        }
    }
}
