package com.mynetty.nettystudy.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * 
 * @author zxl
 * @date 2020/12/22 15:35
 */
public class NioByteBuffer {


    public static void main(String[] args) throws IOException {

//        getTest();
//        readOnlyTest();
        scatterAndGatherTest();

    }

    private static void getTest(){
        try {
            /*
            1. 读取顺序和存放顺序一致
            2. 可以转为只读
            */
            ByteBuffer byteBuffer = ByteBuffer.allocate(64);
            byteBuffer.putInt(100);
            byteBuffer.putLong(9);
            byteBuffer.putChar('测');
            byteBuffer.putShort((short) 4);
            byteBuffer.flip();

            System.out.println(byteBuffer.getChar());
            System.out.println(byteBuffer.getInt());
            System.out.println(byteBuffer.getLong());
            System.out.println(byteBuffer.getLong());

        }catch (BufferUnderflowException e){
            System.out.println("读取顺序和存放顺序一致");
            e.printStackTrace();
        }

    }

    private static void readOnlyTest() {
        try {

            ByteBuffer byteBuffer = ByteBuffer.allocate(64);
            ByteBuffer copyBuffer = byteBuffer.asReadOnlyBuffer();
            byteBuffer.putChar('测');
            // 转为只读之后,存放数据出错
            copyBuffer.putChar('1');
        } catch (ReadOnlyBufferException e){
            System.out.println("更改为只读数组之后，不可修改、存放");
            e.printStackTrace();
        }
    }

    /**
     * scattering: 把数据写入到buffer时，可以采用buffer数组，依次写入【分散】
     * gathering: 从buffer读取数据时，可以采用buffer数组，依次读
     */
    private static void scatterAndGatherTest() throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress interfaceAddress = new InetSocketAddress(5000);

        serverSocketChannel.socket().bind(interfaceAddress);

        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLength = 8;
        while (true){
            System.out.println("线程信息:" + Thread.currentThread().getId() + " 线程名字:" + Thread.currentThread().getName());
            int byteRead = 0;
            while (byteRead < messageLength){
                long l = socketChannel.read(byteBuffers);
                byteRead += l;
                System.out.println("byteRead=" + byteRead);
                //使用流打印
                Arrays.asList(byteBuffers).stream().map(byteBuffer -> "position=" +byteBuffer.position()+",limit="+byteBuffer.limit()+",内容:"+new String(byteBuffer.array())).forEach(System.out::println);
            }

            //所有buffer flip操作
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());

            //将数据显示在客户端
            long byteWrite = 0;
            while (byteWrite < messageLength){
                long write = socketChannel.write(byteBuffers);
                byteWrite += write;
            }

            // 所有buffer 清除操作
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());

            System.out.println("byteRead="+byteRead + " byteWrite=" + byteWrite);
        }


    }
}
