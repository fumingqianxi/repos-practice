package com.itheima.IOstream.reversestream;

import java.io.FileReader;
import java.io.IOException;

public class Demo01FileReader {
    public static void main(String[] args) throws IOException{
        FileReader fr = new FileReader("我是GBK格式编码.txt");
        int len;
        while ((len = fr.read()) != -1) {
            System.out.println((char) len);
        }
    }
}
