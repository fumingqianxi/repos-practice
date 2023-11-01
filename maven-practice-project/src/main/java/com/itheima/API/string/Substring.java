package com.itheima.API.string;

/*
字符串的截取方法：

public String substring(int index)：截取从参数位置一直到字符串末尾，返回新字符串。
public String substring(int begin, int end)：截取从begin开始，一直到end结束，中间的字符串。
备注：[begin,end)，包含左边，不包含右边。
 */
public class Substring {
    public static void main(String[] args) {
        String str1 = "HelloWorld";
        String str2 = str1.substring(5, 10);
        System.out.println(str2);
        System.out.println("====================");
        String str3 = str1.substring(5, 8);
        System.out.println(str3);
    }
}
