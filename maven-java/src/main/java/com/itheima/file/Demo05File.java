package com.itheima.file;

import java.io.File;
import java.io.IOException;

public class Demo05File {
    public static void main(String[] args) throws IOException {
        File f1 = new File("D:\\IdeaProjects\\maven_java\\1.txt");
        boolean b1 = f1.createNewFile();
        System.out.println(b1);
        File f2 = new File("D:\\IdeaProjects\\maven_java\\1.txt");
        boolean b2 = f2.createNewFile();
        System.out.println(b2);
        File f3 = new File("1.txt");
        System.out.println(f3.delete());
    }
}
