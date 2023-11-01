package com.itheima.API.string;

/*
String当中与转换相关的常用方法有：

public char[] toCharArray()：将当前字符串拆分成为字符数组作为返回值。
public byte[] getBytes()：获得当前字符串底层的字节数组。
public String replace(CharSequence oldString, CharSequence newString)：
将所有出现的老字符串替换成为新的字符串，返回替换之后的结果新字符串。
备注：CharSequence意思就是说可以接受字符串类型。
 */
public class StringConvert {
    public static void main(String[] args) {
        char[] chars = "你好".toCharArray();
        System.out.println("你好".charAt(1));
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i]);
        }
        byte[] bytes = "你好".getBytes();
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i]);
        }
        String str = "hellohellohellochar";
        System.out.println(str.replace("hello", "o"));
    }
}
