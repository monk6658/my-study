package com.mynetty.nettystudy.longconnection.client;

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
public class NettyLongClientIdleHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;
            String heartMsg = "0000";
            log.info("心跳包状态: {}",event.state().toString());
            log.info("心跳包: {}",heartMsg);
            ctx.writeAndFlush(Unpooled.copiedBuffer(heartMsg, CharsetUtil.UTF_8));
        }
    }
}
