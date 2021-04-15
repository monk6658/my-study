package com.mynetty.nettystudy.groupchat.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * netty 客户端逻辑类
 * @author zxl
 * @date 2021/3/25 16:52
 */
@Slf4j
public class NettyGroupClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        log.info("收到来自服务端的消息:{}",msg.toString());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.info("铁子，我跑了啊，{}",ctx.channel().localAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("铁子，你咋出错了呢？",cause);
        ctx.close();
    }
}
