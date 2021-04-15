package com.mynetty.nettystudy.normal.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * 基本netty客户端
 * @author zxl
 * @date 2021/3/23 10:21
 */
@Slf4j
public class NettyClient {


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
                            ch.pipeline().addLast(new NettyClientHandler());
                        }
                    });

            // 启动客户端去连接
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6000).sync(); //直到连接返回，才会退出当前线程

            //** 使用监听条件，在connect 或者 bind操作时，不使用sync，调用的sync()的目的就是保证ChannelFuture已经完成了。
            channelFuture.addListener((ChannelFutureListener) future -> {
                if(future.isSuccess()){
                    log.info("链接成功");
                }else {
                    log.info("链接失败");
                }
            });

            // 给关闭通道进行监听 //直到channel关闭，才会退出当前线程
            //通过引入监听器对象监听future状态，当future任务执行完成后会调用-》{}内的方法
            channelFuture.channel().closeFuture().addListener(future -> {
                log.info("Netty Http Server Start Shutdown ............");
                // 优雅关闭
                group.shutdownGracefully();
            });

        }catch (Exception e){
            log.error("客户端启动异常",e);
            group.shutdownGracefully();
        }finally {
            group.shutdownGracefully();
        }

    }




}
