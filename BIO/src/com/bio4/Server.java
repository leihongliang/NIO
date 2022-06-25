package com.bio4;

import java.net.ServerSocket;
import java.net.Socket;


/**
 * 目标：开发实现伪异步通信架构
 */
public class Server {
    public static void main(String[] args) {
        try {
            //1.注册端口
            ServerSocket  ss = new ServerSocket(9991);
            //2.定义衣蛾循环接收客户端的socket链接请求
            //初始化一个线程池对象
            HanlerSocketServerPool pool = new HanlerSocketServerPool(3, 10);
            while (true){
                Socket socket = ss.accept();
                //3.把socket对象交给一个线程池进行处理
                Runnable target = new ServerRunnableTarget(socket);
                pool.execute(target);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
