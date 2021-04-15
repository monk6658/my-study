package com.mynetty.nettystudy.mylengthdecoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

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

        if(msg instanceof byte[]){
            byte[] requestMsg = (byte[]) msg;
            log.info("最终接收数据----:{}",bytesToHex(requestMsg));
        }else{
            ByteBuf byteBuf = (ByteBuf) msg;
            byte[] requestMsg = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(requestMsg);
            log.info("ByteBuf 最终接收数据----:{}",bytesToHex(requestMsg));
        }
    }

    /**
     * 实际数据读取完成处理
     * @param ctx 上下文
     * @throws Exception 执行异常
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("数据读取完毕之后 channelReadComplete ");
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


    /**
     * byte[] 数组转16进制字符串
     * @param bytes 字节数据
     * @return 16进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(aByte & 0xFF);
            if (hex.length() < 2) {
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }

}
