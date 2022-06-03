package com.itheima.collection.generic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Demo05Generic {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        List<String> list2 = new ArrayList<>();
        list2.add("a");
        list2.add("b");
        printArray(list1);
        printArray(list2);
    }

    public static void printArray(List<?> list) {
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
