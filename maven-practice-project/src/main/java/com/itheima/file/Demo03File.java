package com.itheima.file;

import java.io.File;

public class Demo03File {
    public static void main(String[] args) {
        File f1 = new File("D:\\IdeaProjects\\maven_java\\a.txt");
        System.out.println(f1.getAbsolutePath());
        System.out.println(f1.getPath());
        File f2 = new File("a.txt");
        System.out.println(f2.getAbsolutePath());
        System.out.println(f2.getPath());
    }
}
