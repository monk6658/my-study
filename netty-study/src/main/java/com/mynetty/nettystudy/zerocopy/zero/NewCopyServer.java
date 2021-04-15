package com.mynetty.nettystudy.zerocopy.zero;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * nio零拷贝方式服务端
 * @author zxl
 * @date 2020/12/28 14:27
 */
public class NewCopyServer {

    public static void main(String[] args) throws Exception {



        //1. 获取ServerSocketChannel，并绑定端口信息,并设置为非阻塞
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket socket = serverSocketChannel.socket();
        socket.bind(new InetSocketAddress(5000));
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//      阻塞式写法
//        while (true){
//            SocketChannel socketChannel = serverSocketChannel.accept();
//            int readCount = 0;
//            int total = 0;
//            while (readCount != -1){
//                try {
//                    readCount = socketChannel.read(byteBuffer);
//                }catch (Exception e){
//                    break;
//                }
//                byteBuffer.rewind();
//                total += readCount;
//            }
//            System.out.println("总字节数:" + ++total);
//        }

        //2. 非阻塞写法
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            int select = selector.select();
            if(select < 0){
                continue;
            }

            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                SocketChannel socketChannel = null;
                if(key.isAcceptable()){//连接操作
                    socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ,ByteBuffer.allocate(1024));
                    System.out.println("连接客户端：" + socketChannel.getRemoteAddress());
                }
                if(key.isReadable()){//读操作
                    socketChannel = (SocketChannel) key.channel();
                    socketChannel.configureBlocking(false);
                    int read;
                    int total = 0;
                    while ((read = socketChannel.read(byteBuffer)) > 0){
                        total += read;
                        byteBuffer.rewind();
                    }
                    System.out.println("服务端接收总字节:" + total);
                }
                key.cancel();
                keyIterator.remove();
            }
        }

    }


}
