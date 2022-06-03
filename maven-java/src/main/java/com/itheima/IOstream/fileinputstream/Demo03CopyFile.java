package com.itheima.IOstream.fileinputstream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo03CopyFile {
    public static void main(String[] args) throws IOException{
        FileInputStream fis = new FileInputStream("C:\\Users\\HL\\Downloads\\day01【前言、入门程序、常量、变量】.pdf");
        FileOutputStream fos = new FileOutputStream("C:\\Users\\HL\\Downloads\\day01day01【前言】.pdf");
        byte[] bytes = new byte[1024];
        int len;
        while ((len = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }
        fis.close();
        fos.close();
    }
}
