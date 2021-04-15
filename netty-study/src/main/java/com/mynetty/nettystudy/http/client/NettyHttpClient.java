package com.mynetty.nettystudy.http.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author zxl
 * @date 2021/3/24 17:05
 */
@Slf4j
public class NettyHttpClient {

    public static void main(String[] args) {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(bossGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        // http 请求和响应进行解码
                        ch.pipeline().addLast(new LoggingHandler());
                        ch.pipeline().addLast(new HttpClientCodec());
                        ch.pipeline().addLast(new NettyHttpClientHanddler());
                    }
                });

        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1",6000).syncUninterruptibly().addListener(future -> {
            String logBanner = "\n\n" +
                    "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n" +
                    "*                                                                                   *\n" +
                    "*                                                                                   *\n" +
                    "*                   Netty Http Server started on port {}.                           *\n" +
                    "*                                                                                   *\n" +
                    "*                                                                                   *\n" +
                    "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n";
            log.info(logBanner, "6000");
        });


        //通过引入监听器对象监听future状态，当future任务执行完成后会调用-》{}内的方法
        channelFuture.channel().closeFuture().addListener(future -> {
            log.info("Netty Http Server Start Shutdown ............");
            /**
             * 优雅关闭
             */
            bossGroup.shutdownGracefully();
        });


    }

}
