package com.mynetty.nettystudy.groupchat.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Objects;

/**
 * netty 服务端handler
 * @author zxl
 * @date 2021/3/25 15:57
 */
@Slf4j
public class NettyGroupServerHandler extends ChannelInboundHandlerAdapter {

    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channels.add(ctx.channel());
        channels.writeAndFlush(Unpooled.copiedBuffer("某某加入了聊天" + ctx.channel().remoteAddress(), CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        ByteBuf byteBuf = (ByteBuf) msg;
        log.info("来自服务端的消息:{}--{}",ctx.channel().remoteAddress(),msg.toString());

        // 1. 转信息
        Iterator<Channel> iterator = channels.iterator();
        while (iterator.hasNext()){
            Channel next = iterator.next();
            if(Objects.equals(next.id(),ctx.channel().id())){
                continue;
            }

            next.writeAndFlush(ctx.channel().remoteAddress() + "----" + msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("铁子，一路走好！{}",ctx.channel().remoteAddress());
        channels.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("铁子，出错了啊! 关闭该上下文连接",cause);
        ctx.close();
    }
}
