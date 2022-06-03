package com.itheima.API.string;

/*
 String当中与获取相关的常用方法有：
 public int length()：获取字符串当中含有的字符个数，拿到字符串长度。
 public String concat(String str)：将当前字符串和参数字符串拼接成为返回值新的字符串。
 public char charAt(int index)：获取指定索引位置的单个字符。（索引从0开始。）
 public int indexOf(String str)：查找参数字符串在本字符串当中首次出现的索引位置，如果没有返回-1值。
 */
public class StringGet {
    public static void main(String[] args) {
        String str = "asdfasdfasodfnasdf";
        //获取长度
        System.out.println(str.length());

        //拼接字符串
        String str1 = str.concat("11111");
        System.out.println(str1);
        String str2 = str + "11111";
        System.out.println(str2);
        System.out.println(str1 == str2);
        String str3 = "asdfasdfasodfnasdf11111";
        String str4 = "asdfasdfasodfnasdf11111";
        System.out.println(str1 == str3);
        System.out.println(str3 == str4);

        //获取指定位置字符
        System.out.println(str.charAt(11));

        //查找索引位置
        System.out.println(str.indexOf("asdf"));
    }
}
