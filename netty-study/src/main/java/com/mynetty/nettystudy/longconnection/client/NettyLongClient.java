package com.mynetty.nettystudy.longconnection.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * netty长连接客户端
 * @author zxl
 * @date 2021/3/26 11:22
 */
@Slf4j
public class NettyLongClient {

    public static void main(String[] args) {

        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            // 2. 客户端启动配置
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            // 心跳控制器，会在下一个执行
                            ch.pipeline().addLast(new IdleStateHandler(2,0,0, TimeUnit.MINUTES));
                            // 处理idle
                            ch.pipeline().addLast(new NettyLongClientIdleHandler());
                            // 再次捕获idle尝试
                            ch.pipeline().addLast(new NettyLongClientHandler());
                        }
                    });

            // 启动客户端去连接
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6000).sync(); //直到连接返回，才会退出当前线程

            //** 使用监听条件，在connect 或者 bind操作时，不使用sync，调用的sync()的目的就是保证ChannelFuture已经完成了。
            channelFuture.addListener((ChannelFutureListener) future -> {
                if (future.isSuccess()) {
                    log.info("链接成功");
                } else {
                    log.info("链接失败");
                }
            });

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                String next = scanner.next();
                channelFuture.channel().writeAndFlush(Unpooled.copiedBuffer(next, CharsetUtil.UTF_8));
            }


            // 给关闭通道进行监听 //直到channel关闭，才会退出当前线程
            //通过引入监听器对象监听future状态，当future任务执行完成后会调用-》{}内的方法
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            log.error("启动失败，优雅的关闭",e);
            group.shutdownGracefully();
        }


    }



}
