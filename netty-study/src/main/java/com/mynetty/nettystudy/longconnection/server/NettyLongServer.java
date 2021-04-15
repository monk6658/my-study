package com.mynetty.nettystudy.longconnection.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * netty 长连接服务端
 * @author zxl
 * @date 2021/3/26 11:04
 */
@Slf4j
public class NettyLongServer {


    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup(8);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workGroup)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new StringEncoder());
                            ch.pipeline().addLast(new StringDecoder());
                            // 心跳控制器，会在下一个执行。若不设置super继承，idle以及channelRead均只会走下一个,不会走链式流程
                            ch.pipeline().addLast(new IdleStateHandler(3,5,7, TimeUnit.MINUTES));
                            // 处理idle
                            ch.pipeline().addLast(new NettyLongServerIdleHandler());
                            // 再次捕获idle尝试
                            ch.pipeline().addLast(new NettyLongServerHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.bind(6000).sync();

            channelFuture.channel().closeFuture().sync();

        }catch (Exception e){
            log.error("华丽丽的退出",e);
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }


}
