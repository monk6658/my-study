package com.mynetty.nettystudy.zerocopy.zero;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * 零拷贝客户端实现
 * 注：
 * @author zxl
 * @date 2020/12/28 14:43
 */
public class NewCopyClient {


    public static void main(String[] args) throws Exception {

         /*
            常见零拷贝：mmap（内存映射）、sendFile，零拷贝从操作系统角度，是没有CPU拷贝
            mmap：适合小数据量读写，需4次上下文切换，3次数据拷贝。必须从内核拷贝到Socket缓冲区
            sendFile：是和大文件传输，3次上下文切换，2次数据拷贝。可以利用DMA方式，减少CPU拷贝

            DMA(direct memory access):直接内存拷贝（不使用CPU）
         */

        // 1. 使用SocketChannel 连接服务器
        SocketChannel socketChannel = SocketChannel.open();
        boolean connect = socketChannel.connect(new InetSocketAddress("127.0.0.1", 5000));
        if(!connect){
            while (!socketChannel.finishConnect()){
                System.out.println("重复连接操作。。。");
            }
        }

        // 2. 使用零拷贝数据发送给服务器，最多发送8M，故分批次发送
        String fileName = "E:\\tools\\apache-jmeter-5.1.1.zip";
        FileInputStream fis = new FileInputStream(fileName);
        FileChannel fileChannel = fis.getChannel();

        long startTime = System.currentTimeMillis();
        long maxSize = 8*1024;
        long total = 0;
        if(maxSize > fileChannel.size()){
            total = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        }else{
            while (total < fileChannel.size()){
                long transfer = fileChannel.transferTo(total, fileChannel.size(), socketChannel);
                System.out.println("发送：" + transfer + " " + fileChannel.size() + " " + total);
                total += transfer;
            }
        }

        System.out.println("字节:" + total + ", 执行时间:" + (System.currentTimeMillis() - startTime));
        fileChannel.close();
        fis.close();
        socketChannel.close();

    }

}
