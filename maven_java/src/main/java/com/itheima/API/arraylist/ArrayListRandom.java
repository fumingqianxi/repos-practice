package com.itheima.API.arraylist;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
题目：
生成6个1~33之间的随机整数，添加到集合，并遍历集合。
 */
public class ArrayListRandom {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 6; i++) {
            int num = r.nextInt(33) + 1;
            list.add(num);
        }
        //遍历集合
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("第%d个元素：", i) + list.get(i));
        }
    }
}
