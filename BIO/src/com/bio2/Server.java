package com.bio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            System.out.println("服务端启动");
            // 1.定义一个ServerSocket对象进行服务端的端口注册
            ServerSocket ss = new ServerSocket(9999);
            // 2.监听客户端的socket连接请求
            // 监听到这个套接字的连接并接受它。该方法将阻塞，直到建立连接
            System.out.println("等待客户端数据中...");
            Socket socket = ss.accept();// 等待客户端数据
            System.out.println("接收到客户端数据！");
            // 3.在socket管道中得到一个字节输入流对象
            InputStream is = socket.getInputStream();
            // 4.把字节输入流包装成一个缓冲字符输入流
            InputStreamReader isr = new InputStreamReader(is);//字节 -> 字符
            BufferedReader br = new BufferedReader(isr);//缓冲字符输入流
            String msg;
            while ((msg = br.readLine())!=null){
                System.out.println("服务端接收到: " + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
