package com.mynetty.nettystudy.http.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * http server 逻辑类
 * @author zxl
 * @date 2021/3/24 16:58
 */
@Slf4j
public class NettyHttpClientHanddler extends ChannelInboundHandlerAdapter {

//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ByteBuf byteBuf = Unpooled.copiedBuffer("{\"accountName\":\"ceshi\",\"data\":{\"serverIp\":\"127.0.0.1\"},\"interfaceCode\":\"queryHeartbeat\",\"requestTime\":\"1619161276\"}", CharsetUtil.UTF_8);
//        URI uri = new URI("/tripartite/Heartbeat/queryHeartbeat");
//        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.POST, uri.toASCIIString());
//        request.content().writeBytes(byteBuf);
//        request.headers().add(HttpHeaderNames.CONTENT_TYPE,HttpHeaderValues.APPLICATION_JSON);
//        request.headers().add(HttpHeaderNames.CONNECTION,HttpHeaderValues.KEEP_ALIVE);
//        request.headers().add(HttpHeaderNames.CONTENT_LENGTH,request.content().readableBytes());
//        request.headers().add(HttpHeaderNames.HOST,"api.zhongcaisd.cn");
//        ctx.writeAndFlush(request);
//    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws URISyntaxException {
        if(evt instanceof IdleStateEvent){
//            IdleStateEvent event = (IdleStateEvent) evt;
//            String heartMsg = "0000";
//            log.info("心跳包状态: {}",event.state().toString());
//            log.info("心跳包: {}",heartMsg);
//            ctx.writeAndFlush(Unpooled.copiedBuffer(heartMsg, CharsetUtil.UTF_8));
            ByteBuf byteBuf = Unpooled.copiedBuffer("{\"accountName\":\"ceshi\",\"data\":{\"serverIp\":\"127.0.0.1\"},\"interfaceCode\":\"queryHeartbeat\",\"requestTime\":\"1619161276\"}", CharsetUtil.UTF_8);
            URI uri = new URI("/tripartite/Heartbeat/queryHeartbeat");
            FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.POST, uri.toASCIIString());
            request.content().writeBytes(byteBuf);
            request.headers().add(HttpHeaderNames.CONTENT_TYPE,HttpHeaderValues.APPLICATION_JSON);
            request.headers().add(HttpHeaderNames.CONNECTION,HttpHeaderValues.KEEP_ALIVE);
            request.headers().add(HttpHeaderNames.CONTENT_LENGTH,request.content().readableBytes());
            request.headers().add(HttpHeaderNames.HOST,"api.zhongcaisd.cn");
            ctx.writeAndFlush(request);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("最终完成");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        log.info("消息类型: {}",msg.getClass());

        if(msg instanceof HttpResponse){
            HttpResponse httpResponse = (HttpResponse) msg;
            log.info(httpResponse.toString());

        }
        if(msg instanceof HttpContent){
            HttpContent httpContent = (HttpContent) msg;
            log.info("服务端响应:{}",httpContent.content().toString(CharsetUtil.UTF_8));
        }

        // 1. 判断消息类型是否是http格式
        if(msg instanceof HttpRequest){

            log.info("客户端地址: {}", ctx.channel().remoteAddress());

            // 2. 读取客户端请求信息

            // 3. 回复客户端内容
//            ByteBuf byteBuf = Unpooled.copiedBuffer("您好，服务端", CharsetUtil.UTF_8);
//            DefaultFullHttpRequest httpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST,"http://127.0.0.1:6000");
//            httpRequest.content().writeBytes(byteBuf);
//            httpRequest.headers().set(HttpHeaderNames.CONTENT_TYPE,HttpHeaderValues.APPLICATION_JSON);
//            httpRequest.headers().set(HttpHeaderNames.CONTENT_LENGTH,byteBuf.readableBytes());
            //
//            ctx.writeAndFlush(httpRequest);

        }







    }
}
