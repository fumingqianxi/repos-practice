package com.itheima.IOstream.fileoutputstream;

import java.io.FileOutputStream;
import java.io.IOException;

public class Demo01OutputStream {
    public static void main(String[] args) {
        try {
            FileOutputStream fos = new FileOutputStream("a.txt");
            fos.write(97);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
