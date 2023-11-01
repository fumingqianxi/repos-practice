package com.itheima.IOstream.readerandwriter;

import java.io.FileWriter;
import java.io.IOException;

public class Demo01Writer {
    public static void main(String[] args) throws IOException{
        FileWriter fw = new FileWriter("c.txt");
        fw.write(97);
        fw.flush();
    }
}
