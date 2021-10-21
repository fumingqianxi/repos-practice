package com.itheima;

import java.text.ParseException;
import java.util.Objects;
import java.util.UUID;

public class DemoTest {
    public static void main(String[] args) throws ParseException {
        String str = "aaa bbb";
        int count = -1;
        for (int i = 0; i < str.length(); i ++) {
            if ((str.charAt(i)+"").equals(" ")) {
                if (str.substring(0, i).equals(str.substring(i + 1))){
                    count = i;
                    break;
                }
            }
        }
        System.out.println(count);
        if (count == -1) {
            System.out.println(str);
        } else {
            System.out.println(str.substring(0, count));
        }
    }
}
