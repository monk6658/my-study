package com.mynetty.nettystudy.longconnection.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * netty 长连接服务端处理器
 * @author zxl
 * @date 2021/3/26 11:09
 */
@Slf4j
public class NettyLongServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        log.info(evt.toString());
        if(evt instanceof IdleStateEvent){
            log.info("xint NettyLongServerHandler");
            ctx.writeAndFlush(Unpooled.copiedBuffer("心跳包", CharsetUtil.UTF_8));
        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("自定义控制器二，接收数据:{}",msg.toString());
    }

}
