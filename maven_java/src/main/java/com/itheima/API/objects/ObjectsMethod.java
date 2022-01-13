package com.itheima.API.objects;

import java.util.Objects;

public class ObjectsMethod {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = null;
//        boolean b = s1.equals(s2);
//        boolean b2 = s2.equals(s1);
        boolean b = java.util.Objects.equals(s1, s2);
        boolean b2 = java.util.Objects.equals(s2, s1);
        System.out.println(b);
        System.out.println(b2);
    }
}
