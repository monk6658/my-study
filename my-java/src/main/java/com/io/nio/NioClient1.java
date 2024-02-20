package com.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author: zxl
 * @create: 2024-02-19 15:10
 **/
public class NioClient1 {

    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9999));
        
        socketChannel.configureBlocking(false);

        Scanner sc = new Scanner(System.in);
        
        
        while (true){

            String s = sc.nextLine();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put(s.getBytes(StandardCharsets.UTF_8));
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        
    
        
        
        
        
        
    }
    
}
