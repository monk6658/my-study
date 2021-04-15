package com.mynetty.nettystudy.http.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.util.Objects;

/**
 * http server 逻辑类
 * @author zxl
 * @date 2021/3/24 16:58
 */
@Slf4j
public class NettyHttpServerHanddler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("消息类型: {}",msg.getClass());

        // 1. 判断消息类型是否是http格式
        if(msg instanceof HttpRequest){

            log.info("客户端地址: {}", ctx.channel().remoteAddress());

            // 2. 读取客户端请求信息

            HttpRequest httpRequest = (HttpRequest) msg;
            URI uri = new URI(httpRequest.uri());

            // 根据地址拦截
            if(Objects.equals("/favicon.ico",uri.getPath())){
                log.warn("过滤拦截");
                return;
            }

            // 3. 回复客户端内容

            ByteBuf byteBuf = Unpooled.copiedBuffer("您好，服务端", CharsetUtil.UTF_8);

            DefaultFullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,byteBuf);

            httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=utf-8");
            httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH,byteBuf.readableBytes());

            //
            ctx.writeAndFlush(httpResponse);

        }
    }
}
