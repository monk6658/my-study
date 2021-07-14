package com.mynetty.nettystudy.http.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * netty http服务
 * @author zxl
 * @date 2021/3/24 16:51
 */
@Slf4j
public class NettyHttpServer {


    public static void main(String[] args) {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(8);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new LoggingHandler());

                            // http 请求和响应进行解码
                            ch.pipeline().addLast(new HttpServerCodec());

                            ch.pipeline().addLast(new NettyHttpServerHanddler());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.bind(6001).sync();

            channelFuture.channel().closeFuture().sync();

            log.info("启动成功");
        } catch (InterruptedException e) {
            log.error("启动异常", e);
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }



    }







}
