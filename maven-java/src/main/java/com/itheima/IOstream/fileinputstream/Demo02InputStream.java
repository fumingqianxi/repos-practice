package com.itheima.IOstream.fileinputstream;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class Demo02InputStream {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("b.txt");
        byte[] bytes = new byte[3];
        int len = fis.read(bytes);
        System.out.println(len);
        System.out.println(new String(bytes));
        while ((len = fis.read(bytes)) != -1) {
            System.out.println(len);
            System.out.println(new String(bytes));
        }
    }
}
