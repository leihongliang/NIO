# BIO

## bio1

一次传递一条数据

客户端：println

服务端：if

## bio2

多发多收

## bio3

实现服务端可以同时接收多个客户端的socket通信需求

## bio4

开发实现伪异步通信架构，一个线程池封装多个线程
将客户端的Socket封装成一个Task(该任务实现Java. lang. Runnable(线程任务接口）交给后端的线程池中进行处理

## file

实现客户端上传任意类型的文件数据给服务端保存起来

## chat

BIO模式下的端口转发思想
