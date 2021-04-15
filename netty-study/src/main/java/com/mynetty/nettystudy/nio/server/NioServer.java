package com.mynetty.nettystudy.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * @author zxl
 * @date 2020/12/23 15:13
 */
public class NioServer {


    public static void main(String[] args) throws IOException {

        // 创建 serverSocketChannel -> ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //得到一个 Selector 对象
        Selector selector = Selector.open();

        //绑定一个端口 5000，在服务器监听
        serverSocketChannel.socket().bind(new InetSocketAddress(5000));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 把 serverSocketChannel 注册到 selector 关心事件为 OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("注册后 SelectionKeys的数量" + selector.keys().size());

        //循环等待客户端连接
        while(true){
            //等待一秒没有事件发生，返回
            if(selector.select(1000) == 0){// 没有事件发生
                System.out.println("一秒检测，无连接");
                continue;
            }

            /*
            如果返回>0，获取相关selectionKey 集合
            1. 如果返回>0，表示已经获取到关注的事件
            2. selector.selectedKeys() 返回关注事件的集合
            通过selectionKeys 可以反向获取到通道
             */
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();

                //根据key对应的通道发生事件做相应的处理
                if(selectionKey.isAcceptable()){//如果是OP_ACCEPT ，新客户端连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("连接事件进来,产生一个 socketChannel " + socketChannel.hashCode());
                    //设置为非阻塞
                    socketChannel.configureBlocking(false);
                    //将socketChannel注册到selector上，关注事件为OP_READ，同时给socketChannel关联到buffer
                    socketChannel.register(selector,SelectionKey.OP_READ,ByteBuffer.allocate(1024));
                    System.out.println("客户端连接后，注册SelectionKeys的数量" + selector.keys().size());
                }

                if(selectionKey.isReadable()){// 如果是OP_READ
                    //通过key 反向获取到对应的channel
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    //获取channel关联的buffer
                    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                    socketChannel.read(byteBuffer);
                    System.out.println("from 客户端:" + new String(byteBuffer.array()));
                    socketChannel.register(selector,SelectionKey.OP_WRITE,ByteBuffer.allocate(1024));
                }

//                if(selectionKey.isWritable()){
//                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
//                    socketChannel.configureBlocking(false);
//                    String str = "服务端返回数据处理";
//                    socketChannel.write(ByteBuffer.wrap(str.getBytes()));
//                    System.out.println("to 客户端:" + str);
//                }

                // 去除掉当前SelectionKey，防止重复操作
                selectionKey.cancel();
                iterator.remove();
            }

        }


    }

}
