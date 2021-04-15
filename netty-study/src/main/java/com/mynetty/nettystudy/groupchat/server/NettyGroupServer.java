package com.mynetty.nettystudy.groupchat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * netty 服务端
 * @author zxl
 * @date 2021/3/25 15:54
 */
@Slf4j
public class NettyGroupServer {

    private static int port = 6000;

    public static void main(String[] args) {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup(8);
        try {

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new StringEncoder());
                            ch.pipeline().addLast(new NettyGroupServerHandler());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.bind(port).sync();

            if(channelFuture.isSuccess()){
                log.info("项目启动成功，端口为：{}",port);
            }

            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            log.error("启动出错，优雅关闭",e);
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();

        }





    }


}
