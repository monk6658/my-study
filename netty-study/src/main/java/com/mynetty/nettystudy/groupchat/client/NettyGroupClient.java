package com.mynetty.nettystudy.groupchat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Scanner;

/**
 * netty 群聊 客户端
 * @author zxl
 * @date 2021/3/25 16:44
 */
@Slf4j
public class NettyGroupClient {

    private static String ip = "127.0.0.1";
    private static int port = 6000;
    public static void main(String[] args) {

        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new StringEncoder());
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new NettyGroupClientHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(ip, port).sync();
            if(channelFuture.isSuccess()){
                log.info("铁子，我上线了，{}",channelFuture.channel().localAddress());
            }

            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNext()){
                String next = scanner.next();
                if(Objects.equals("exit",next)){
                    break;
                }
                channelFuture.channel().writeAndFlush(Unpooled.copiedBuffer(next, CharsetUtil.UTF_8));
            }


        }catch (Exception e){
            log.error("启动失败，优雅的关闭了!",e);
            group.shutdownGracefully();
        }
    }



}
