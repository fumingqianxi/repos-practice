package com.itheima.net.bstcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        while (true) {
            Socket socket = server.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream is = socket.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        String line = br.readLine();
                        System.out.println(line);
                        String[] arr = line.split(" ");
                        String htmlPath = arr[1].substring(1);
                        FileInputStream fis = new FileInputStream(htmlPath);
                        OutputStream os = socket.getOutputStream();
                        os.write("HTTP/1.1 200 OK\r\n".getBytes());
                        os.write("Content-Type:text/html\r\n".getBytes());
                        // 必须要写入空行,否则浏览器不解析
                        os.write("\r\n".getBytes());
                        //一读一写复制文件,把服务读取的html文件回写到客户端
                        int len;
                        byte[] bytes = new byte[1024];
                        while((len = fis.read(bytes))!=-1){
                            os.write(bytes,0,len);
                        }
                        //释放资源
                        fis.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
