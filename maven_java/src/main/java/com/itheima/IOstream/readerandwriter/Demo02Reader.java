package com.itheima.IOstream.readerandwriter;

import java.io.FileReader;
import java.io.IOException;

public class Demo02Reader {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("b.txt");
        int len;
        while ((len = fr.read()) != -1) {
            System.out.println((char) len);
        }
    }
}
