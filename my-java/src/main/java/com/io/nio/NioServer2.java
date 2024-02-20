package com.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author: zxl
 * @create: 2024-02-19 16:32
 **/
public class NioServer2 {
    
    static ServerSocketChannel serverSocketChannel = null;
    static Selector selector = null;
    public static void main(String[] args) throws IOException {

        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1",9999));
        serverSocketChannel.configureBlocking(false);
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        
        while (selector.select() > 0){

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isAcceptable()){
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else if(selectionKey.isReadable()){

                    sendAll(selectionKey);
                    
                }
                iterator.remove();
            }

        }
    }

    public static void sendAll(SelectionKey selectionKey){

        SocketChannel channel1 = (SocketChannel) selectionKey.channel();

        for (SelectionKey selectedKey : selector.selectedKeys()) {
            if(selectedKey.channel() == channel1){
                continue;
            }
            SocketChannel channel = (SocketChannel) selectedKey.channel();
            try {
                channel.configureBlocking(false);

                ByteBuffer byteBuffer = ByteBuffer.allocate(3);
                int len = 0;
                while ((len = channel1.read(byteBuffer)) > 0){
                    byteBuffer.flip();
                    channel.write(byteBuffer);
                    byteBuffer.clear();
                }
                
            } catch (IOException e) {
                e.printStackTrace();
                
                
                
                
            }


        }
        

    }
    
    
    

}
