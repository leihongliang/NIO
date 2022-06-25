package com.buffer;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 目标：对缓冲区Buffer的常用API进行案例实现。

 Buffer clear() 清空缓冲区并返回对缓冲区的引用
 Buffer flip() 为 将缓冲区的界限设置为当前位置，并将当前位置充值为 0
 int capacity() 返回 Buffer 的 capacity 大小
 boolean hasRemaining() 判断缓冲区中是否还有元素
 int limit() 返回 Buffer 的界限(limit) 的位置
 Buffer limit(int n) 将设置缓冲区界限为 n, 并返回一个具有新 limit 的缓冲区对象
 Buffer mark() 对缓冲区设置标记
 int position() 返回缓冲区的当前位置 position
 Buffer position(int n) 将设置缓冲区的当前位置为 n , 并返回修改后的 Buffer 对象
 int remaining() 返回 position 和 limit 之间的元素个数
 Buffer reset() 将位置 position 转到以前设置的 mark 所在的位置
 Buffer rewind() 将位置设为为 0， 取消设置的 mark
 ByteBuffer compact() :方法将所有未读的数据拷贝到Buffer起始处。然后将position设到最后一个未读元素正后面
 */
public class buffer {
    @Test
    public void test01(){
        // 1、分配一个缓冲区，容量设置成10
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.position()); // 0
        System.out.println(buffer.limit());    // 10
        System.out.println(buffer.capacity()); // 10
        System.out.println("--------------------------");

        // 2、put往缓冲区中添加数据
        String name = "abcdefg";
        buffer.put(name.getBytes());
        System.out.println(buffer.position()); // 7
        System.out.println(buffer.limit());    // 10
        System.out.println(buffer.capacity()); // 10
        System.out.println("--------------------------");

        // 3、Buffer flip() 为 将缓冲区的界限设置为当前位置，并将当前位置设值为 0: 可读模式
        buffer.flip();
        System.out.println(buffer.position()); // 0
        System.out.println(buffer.limit());    // 7
        System.out.println(buffer.capacity()); // 10
        System.out.println("--------------------------");

        // 4、get数据的读取
        char ch = (char) buffer.get();
        System.out.println(ch);                // a
        System.out.println(buffer.position()); // 1
        System.out.println(buffer.limit());    // 7
        System.out.println(buffer.capacity()); // 10
    }

    @Test
    public void test02(){
        // 1、分配一个缓冲区，容量设置成10
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.position()); // 0
        System.out.println(buffer.limit());    // 10
        System.out.println(buffer.capacity()); // 10
        System.out.println("1----------------------------");
        String name = "abcdefg";
        buffer.put(name.getBytes());
        System.out.println(buffer.position()); // 7 中文/字符，为3；数字为1
        System.out.println(buffer.limit());    // 10
        System.out.println(buffer.capacity()); // 10
        System.out.println("2--------------------------");
        // 2、clear清除缓冲区中的数据
        buffer.clear();
        System.out.println(buffer.position()); // 0
        System.out.println(buffer.limit());    // 10
        System.out.println(buffer.capacity()); // 10
        System.out.println((char)buffer.get());// a

        // 3、定义一个缓冲区
        ByteBuffer buf = ByteBuffer.allocate(10);
        String n = "abcdefg";
        buf.put(n.getBytes());
        buf.flip();
        // 读取数据
        byte[] b = new byte[2];
        buf.get(b);
        String rs = new String(b);
        System.out.println(rs);// ab
        System.out.println(buf.position()); // 2
        System.out.println(buf.limit());    // 7
        System.out.println(buf.capacity()); // 10
        buf.mark(); // 标记此刻这个位置！ 2
        byte[] b2 = new byte[3];
        buf.get(b2);
        System.out.println(new String(b2)); // cde
        System.out.println(buf.position()); // 5
        buf.reset(); // 回到标记位置
        System.out.println(buf.position()); // 2
        if(buf.hasRemaining()){
            System.out.println(buf.remaining()); // 5
        }
    }

    @Test
    public void test03(){
        // 1、创建一个直接内存的缓冲区/con
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);
        System.out.println(buffer.isDirect());
    }
}
