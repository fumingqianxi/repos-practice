package com.itheima.IOstream.fileinputstream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Demo01InputStream {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("a.txt");
        int len;
        while ((len = fis.read()) != -1) {
            System.out.println(len);
        }
        System.out.println((char) 1000000000);
        fis.close();
    }
}
