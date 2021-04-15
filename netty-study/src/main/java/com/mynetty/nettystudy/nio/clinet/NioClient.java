package com.mynetty.nettystudy.nio.clinet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 
 * @author zxl
 * @date 2020/12/24 16:27
 */
public class NioClient {

    public static void main(String[] args) throws IOException {

        //得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();

        //设置为非阻塞
        socketChannel.configureBlocking(false);

        //连接服务器
        if(!socketChannel.connect(new InetSocketAddress("127.0.0.1",5000))){
            //connect连接失败的话，使用finishConnect重连
            while (!socketChannel.finishConnect()){
                System.out.println("因为连接需要时间，客户端不会阻塞，可以做其他工作、、、");
            }
        }

        //如果连接成功，发送数据
        String str = "非阻塞nio客户端发送数据测试";
        System.out.println(str);
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());

        socketChannel.write(byteBuffer);

//        System.in.read();
        socketChannel.close();

    }

}
