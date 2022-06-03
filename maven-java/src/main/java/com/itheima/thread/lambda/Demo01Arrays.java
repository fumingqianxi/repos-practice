package com.itheima.thread.lambda;

import java.util.Arrays;
import java.util.Comparator;

public class Demo01Arrays {
    public static void main(String[] args) {
        Person[] arr = new Person[3];
        arr[0] = new Person("柳岩", 38);
        arr[1] = new Person("迪丽热巴", 18);
        arr[2] = new Person("古力娜扎", 19);
        Arrays.sort(arr, Comparator.comparingInt(Person::getAge));
        System.out.println(Arrays.asList(arr));
    }
}
