package com.mynetty.nettystudy.http.client;

import com.sxm.common.util.IpUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author zxl
 * @date 2021/3/24 17:05
 */
@Slf4j
public class NettyHttpClient {

    public static void main(String[] args) throws IOException, InterruptedException {

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
                        ch.pipeline().addLast(new HttpServerCodec());
                        ch.pipeline().addLast(new IdleStateHandler(5,0,0, TimeUnit.SECONDS));
                        ch.pipeline().addLast(new NettyHttpClientHanddler());
                    }
                });

        String href = "http://api.zhongcaisd.cn/tripartite/Heartbeat/queryHeartbeat";
        // 端口号
        int port = IpUtil.parsePort(href);
        // 域名
        String host = IpUtil.parseHost(href);
        // IP 地址
        String address = IpUtil.parseIp(host);

        ChannelFuture channelFuture = bootstrap.connect(address,port).syncUninterruptibly().addListener(future -> {
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

        channelFuture.channel().closeFuture().sync();
        //通过引入监听器对象监听future状态，当future任务执行完成后会调用-》{}内的方法
//        channelFuture.channel().closeFuture().addListener(future -> {
//            log.info("Netty Http Server Start Shutdown ............");
//            /**
//             * 优雅关闭
//             */
//            bossGroup.shutdownGracefully();
//        });


    }

}
