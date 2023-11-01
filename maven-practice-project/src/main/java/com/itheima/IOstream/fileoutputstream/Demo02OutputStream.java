package com.itheima.IOstream.fileoutputstream;

import java.io.FileOutputStream;
import java.io.IOException;

public class Demo02OutputStream {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("b.txt");
        fos.write(49);
        fos.write(48);
        fos.write(48);
        byte[] bytes = {66, 67, 68, 69};
        fos.write(bytes);
        for (int i = 0; i < 10; i++) {
            fos.write("你好".getBytes());
            fos.write("\r\n".getBytes());
        }
        fos.close();
    }
}
