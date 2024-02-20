package com.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author: zxl
 * @create: 2024-02-19 15:09
 **/
public class NioServer1 {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1",9999));
        // 非阻塞
        serverSocketChannel.configureBlocking(false);
        
        Selector selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        
        // 阻塞选择
        while (selector.select() > 0){

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            
            while (iterator.hasNext()){
                SelectionKey selectedKey = iterator.next();

                if(selectedKey.isAcceptable()){

                    SocketChannel channel = serverSocketChannel.accept();
                    channel.configureBlocking(false);
                    channel.register(selector,SelectionKey.OP_READ);

                }else if(selectedKey.isReadable()){

                    SocketChannel channel = (SocketChannel) selectedKey.channel();

                    ByteBuffer byteBuffer = ByteBuffer.allocate(3);
                    int len = 0;
                    while ((len = channel.read(byteBuffer)) > 0){
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(),0,len));
                        byteBuffer.clear();
                    }
                }
                iterator.remove();
            }

   
            
        }
        
        
        
        
        
    }
    
    
}
