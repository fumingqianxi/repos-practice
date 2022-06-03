package com.itheima.API.arrays;

import java.util.Arrays;

public class ArraysPractise {
    public static void main(String[] args) {
        String str = "asdfqer1231vasdvasf";
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[chars.length - 1 - i]);
        }
    }
}
