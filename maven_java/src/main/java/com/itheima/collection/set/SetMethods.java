package com.itheima.collection.set;

import java.util.HashSet;
import java.util.Set;

public class SetMethods {
    public static void main(String[] args) {
        show01();
        System.out.println("==============");
        show02();
    }


    /**
     * 基本方法：
     * 将元素添加进Set<E>：boolean add(E e)
     * 将元素从Set<E>删除：boolean remove(Object e)
     * 判断是否包含元素：boolean contains(Object e)
     */
    public static void show01() {
        Set<String> set = new HashSet<>();
        System.out.println(set.add("abc")); // true
        System.out.println(set.add("xyz")); // true
        System.out.println(set.add("xyz")); // false，添加失败，因为元素已存在
        System.out.println(set.contains("xyz")); // true，元素存在
        System.out.println(set.contains("XYZ")); // false，元素不存在
        System.out.println(set.remove("hello")); // false，删除失败，因为元素不存在
        System.out.println(set.size()); // 2，一共两个元素
    }

    //遍历
    public static void show02() {
        Set<String> set = new HashSet<>();
        set.add("apple");
        set.add("banana");
        set.add("pear");
        set.add("orange");
        for (String s : set) {
            System.out.println(s);
        }
    }
}
