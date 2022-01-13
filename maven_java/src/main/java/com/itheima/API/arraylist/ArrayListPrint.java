package com.itheima.API.arraylist;

import java.util.ArrayList;
import java.util.List;

public class ArrayListPrint {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("王五");
        list.add("李四");
        System.out.println(printArrayList(list));
    }

    public static String printArrayList(List<String> list) {
        String result = "{";
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                result += list.get(i);
            } else {
                result += list.get(i) + "@";
            }
        }
        result += "}";
        return result;
    }
}
