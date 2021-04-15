package com.mynetty.nettystudy.normal.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * netty 服务端逻辑处理类
 * @author zxl
 * @date 2021/3/23 10:29
 */
@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 连接注册时执行
     * @param ctx 上下文
     * @throws Exception 执行异常
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端连接{}",ctx.channel().remoteAddress().toString());
        super.channelRegistered(ctx);
    }

    /**
     * 连接关闭时执行
     * @param ctx 上下文
     * @throws Exception 执行异常
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端连接断开了{}",ctx.channel().remoteAddress().toString());
        super.channelUnregistered(ctx);
    }

    /**
     * 连接活跃
     * @param ctx 上下文
     * @throws Exception 执行异常
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("连接活跃，有信息进入");
    }

    /**
     * 连接不活跃
     * @param ctx 上下文
     * @throws Exception 执行异常
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("连接不活跃，信息写完发送完毕");
    }

    /**
     * 实际数据读取处理
     * @param ctx 上下文
     * @throws Exception 执行异常
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf buf = (ByteBuf) msg;
        log.info("数据读取{}",buf.toString(CharsetUtil.UTF_8));

        // 1. 普通任务提交，该任务提交到 当前NIOEventLoop 的 taskQueue, 同一条线程内，一直等到上一个完成再执行
        EventLoop eventExecutors = ctx.channel().eventLoop();
        eventExecutors.execute(()->{
            try {
                Thread.sleep(5 * 1000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("睡眠之后发送", CharsetUtil.UTF_8));
            }catch (Exception e){
                log.error("睡眠异常",e);
            }
        });

        // 2. 自定义定时任务，提交到 scheduleTaskQueue 中
        eventExecutors.schedule(()->{
            ctx.writeAndFlush(Unpooled.copiedBuffer("定时任务发送", CharsetUtil.UTF_8));
        },5, TimeUnit.MILLISECONDS);



    }

    /**
     * 实际数据读取完成处理
     * @param ctx 上下文
     * @throws Exception 执行异常
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        String msg = "读取完毕了，我说";
        log.info("数据读取完毕之后 channelReadComplete {}",msg);
        ctx.writeAndFlush(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
    }

    /**
     * 连接事件判断
     * @param ctx 上下文
     * @param evt 事件对象
     * @throws Exception 执行异常
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        log.info("channel 用户事件触发");
    }

    /**
     * 实际数据读取完成处理 ,关闭通道
     * @param ctx 上下文
     * @param cause 执行异常
     * @throws Exception 执行异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("执行中异常",cause);
        ctx.close();
    }
}
