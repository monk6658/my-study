package com.mynetty.nettystudy.normal.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.NettyRuntime;
import lombok.extern.slf4j.Slf4j;

/**
 * 简单netty服务端
 * @author zxl
 * @date 2021/3/23 10:21
 */
@Slf4j
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {

        // 1. 创建两个工作线程组，两个无线循环(默认线程组为   cpu核数 * 2 )
        System.out.println(NettyRuntime.availableProcessors());

        EventLoopGroup bossGroup = new NioEventLoopGroup(1); // 处理客户端的链接
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);  // 网络的读写

        try {
            // 2. 服务启动配置
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)  // 使用NioServerSocketChannel 作为服务端的通道实现
                    .option(ChannelOption.SO_BACKLOG, 128)  // 设置线程队列连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true)  // 设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {  //创建一个通道初始化对象
                        @Override
                        protected void initChannel(SocketChannel ch) { // 给pipeline设置处理器
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.bind(6000).sync();

            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            log.error("启动异常，优雅关闭",e);
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }


}
