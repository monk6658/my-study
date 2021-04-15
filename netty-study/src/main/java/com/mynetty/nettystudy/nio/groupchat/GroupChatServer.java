package com.mynetty.nettystudy.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * 群聊服务系统
 * @author zxl
 * @date 2020/12/25 10:01
 */
public class GroupChatServer {

    private ServerSocketChannel serverSocketChannel;

    private Selector selector;

    public GroupChatServer()  {
        try {
            //得到一个选择器
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            //绑定端口
            int port = 5000;
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            //设置为非阻塞
            serverSocketChannel.configureBlocking(false);
            //将serverSocketChannel注册到Selector中
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听程序
     */
    private void listen(){
        try {
            //循环处理
            while (true){
                int read = selector.select();
                if(read > 0){//代表当前有通道连接
                    //遍历得到SelectionKey
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){//有事件处理
                        SelectionKey key = iterator.next();
                        if(key.isAcceptable()){//如果连接事件
                            SocketChannel sc = serverSocketChannel.accept();
                            //设置为非阻塞
                            sc.configureBlocking(false);
                            sc.register(selector,SelectionKey.OP_READ);
                            //给出提示
                            System.out.println(sc.getRemoteAddress() + " 上线了...");
                        }
                        if(key.isReadable()){//如果是读事件
                            readData(key);
                        }
                        // 删除当前SelectionKey，防止重复操作
                        iterator.remove();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 读取客户端数据
     * @param selectionKey s
     */
    private void readData(SelectionKey selectionKey){
        //定一个SocketChannel
        SocketChannel socketChannel = null;
        try {
            //1.通过selectionKey 反向获取到 socketChannel
            socketChannel = (SocketChannel) selectionKey.channel();
            socketChannel.configureBlocking(false);


            //2.获取channel关联的buffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(5);
            int read = socketChannel.read(byteBuffer);

            //3.数据处理
            if(read > 0){
                // 把缓存区数据转换成字符串
                String msg = new String(byteBuffer.array());
                System.out.println("from 客户端：" + msg);
                //向其他客户端转发消息
                sendInfoOtherClients(msg,socketChannel);
            }
        }catch (Exception e){
            e.printStackTrace();
            try {
                System.out.println(socketChannel.getRemoteAddress() + " 下线了。。。");
                //取消注册
                selectionKey.cancel();
                // 关闭通道
                socketChannel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 转发消息给其他客户（通道）
     * @param msg 消息
     * @param self 通道
     */
    private void sendInfoOtherClients(String msg,SocketChannel self) throws IOException {
        System.out.println("服务器转发消息中。。。");
        //遍历 所有注册到selector 上的SocketChannel,并且排除self（自己）
        for (SelectionKey key : selector.keys()) {
            // 通过key 获取到 SocketChannel
            Channel channel = key.channel();
            // 类型判断，且排除自己
            if(channel instanceof SocketChannel && channel != self){
                SocketChannel sc = (SocketChannel) channel;
                sc.write(ByteBuffer.wrap(msg.getBytes()));
            }
        }
    }

    public static void main(String[] args) {

        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();

    }

}
