package com.mynetty.nettystudy.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 群聊系统客户端
 * @author zxl
 * @date 2020/12/25 10:32
 */
public class GroupChatClient {

    private SocketChannel socketChannel;

    private Selector selector;
    private String username;
    private String ip = "127.0.0.1";
    private int port = 5000;


    public GroupChatClient(){
        try {

            selector = Selector.open();
            //连接服务器
            socketChannel = SocketChannel.open(new InetSocketAddress(ip,port));
            //设置非阻塞
            socketChannel.configureBlocking(false);
            //注册到 selector，监听事件为 OP_READ
            socketChannel.register(selector, SelectionKey.OP_READ);
            username = socketChannel.getLocalAddress().toString();

            System.out.println("我是" + username);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向服务端发送消息
     */
    private void sendInfo(String info){
        info = username + " 说：" + info;
        try {
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 读取服务端信息
     */
    private void readInfo(){
        try {
            int read = selector.select();
            if(read > 0){//有可以用的通道
                //遍历SelectionKey
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if(key.isReadable()){//如果是可读事件
                        //获取SocketChannel通道
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        //设置非阻塞
                        socketChannel.configureBlocking(false);
                        //把服务器数据，读取到byteBuffer 中
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        socketChannel.read(byteBuffer);
                        System.out.println(new String(byteBuffer.array()));
                    }
                    iterator.remove();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        GroupChatClient groupChatClient = new GroupChatClient();
        // 客户端每隔3s ，读取服务器数据
        new Thread(() -> {
            while (true){
                groupChatClient.readInfo();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 客户端发送数据处理
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String msg = scanner.nextLine();
            groupChatClient.sendInfo(msg);
        }



    }

}
