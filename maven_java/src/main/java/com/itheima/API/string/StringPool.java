package com.itheima.API.string;

public class StringPool {

    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "abc";

        char[] chars = {'a', 'b', 'c'};
        String str3 = new String(chars);

        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
        System.out.println(str2 == str3);
    }
}
