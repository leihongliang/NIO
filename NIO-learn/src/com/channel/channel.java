package com.channel;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
public class channel {
    @Test
    /**文件写入**/
    public void write(){
        try {
            // 1、字节输出流通向目标文件
            FileOutputStream fos = new FileOutputStream("src/com/channel/data01.txt");
            // 2、得到字节输出流对应的通道Channel
            FileChannel channel = fos.getChannel();
            // 3、分配缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("缓冲区注入数据！".getBytes());
            // 4、把缓冲区切换成写出模式!!!
            buffer.flip();
            channel.write(buffer);
            System.out.println("写入完成！");
            channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    /**文件读取**/
    public void read() throws Exception {
        // 1、定义一个文件字节输入流与源文件接通
        FileInputStream is = new FileInputStream("src/com/channel/data01.txt");
        // 2、需要得到文件字节输入流的文件通道
        FileChannel channel = is.getChannel();
        // 3、定义一个缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 4、读取数据到缓冲区!!!
        channel.read(buffer);
        buffer.flip();
        // 5、读取出缓冲区中的数据并输出即可
        String rs = new String(buffer.array(),0,buffer.remaining());
        System.out.println(rs);
    }

    @Test
    /**文件复制**/
    public void copy() throws Exception {
        // 源文件
        File srcFile = new File("E:\\HDU\\Java\\Learn\\NIO\\BIO\\src\\com\\file\\Input\\java.png");
        File destFile = new File("E:\\HDU\\Java\\Learn\\NIO\\BIO\\src\\com\\file\\Input\\java_new.png");
        // 得到一个字节字节输入流
        FileInputStream fis = new FileInputStream(srcFile);
        // 得到一个字节输出流
        FileOutputStream fos = new FileOutputStream(destFile);
        // 得到的是文件通道
        FileChannel isChannel = fis.getChannel();
        FileChannel osChannel = fos.getChannel();
        // 分配缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while(true){
            // 必须先清空缓冲然后再写入数据到缓冲区
            buffer.clear();
            // 开始读取一次数据
            int flag = isChannel.read(buffer);
            if(flag == -1){
                break;
            }
            // 已经读取了数据 ，把缓冲区的模式切换成可读模式
            buffer.flip();
            // 把数据写出到
            osChannel.write(buffer);
        }
        isChannel.close();
        osChannel.close();
        System.out.println("复制完成！");
    }

    @Test
    /**分散和聚集*/
    public void test() throws Exception {
        // 1、字节输入管道
        FileInputStream is = new FileInputStream("src/com/channel/data01.txt");
        FileChannel isChannel = is.getChannel();
        // 2、字节输出流管道
        FileOutputStream fos = new FileOutputStream("src/com/channel/data02.txt");
        FileChannel osChannel = fos.getChannel();
        // 3、定义多个缓冲区做数据分散
        ByteBuffer buffer1 = ByteBuffer.allocate(4);
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);
        ByteBuffer[] buffers = {buffer1 , buffer2};
        // 4、从通道中读取数据分散到各个缓冲区
        isChannel.read(buffers);
        // 5、从每个缓冲区中查询是否有数据读取到了
        for(ByteBuffer buffer : buffers){
            buffer.flip();// 切换到读数据模式
            System.out.println(new String(buffer.array() , 0 , buffer.remaining()));
        }
        // 6、聚集写入到通道
        osChannel.write(buffers);
        isChannel.close();
        osChannel.close();
        System.out.println("文件复制~~");
    }

    @Test
    /**transferFrom*/
    public void test02() throws Exception {
        // 1、字节输入管道，原通道
        FileInputStream is = new FileInputStream("data01.txt");
        FileChannel isChannel = is.getChannel();
        // 2、字节输出流管道。目标通道
        FileOutputStream fos = new FileOutputStream("data04.txt");
        FileChannel osChannel = fos.getChannel();
        // 3、复制数据 isChannel -> osChannel
        // osChannel.transferFrom(isChannel , isChannel.position() , isChannel.size());
        isChannel.transferTo(isChannel.position() , isChannel.size() , osChannel);
        isChannel.close();
        osChannel.close();
        System.out.println("完成复制！");
    }








}
