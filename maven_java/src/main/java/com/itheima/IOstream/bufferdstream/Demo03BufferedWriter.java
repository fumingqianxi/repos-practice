package com.itheima.IOstream.bufferdstream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Demo03BufferedWriter {
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter("f.txt"));
        for (int i = 0; i < 10; i++) {
            bw.write("传智博客");
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}
