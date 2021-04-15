package com.mynetty.nettystudy.longconnection.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * netty 长连接服务端处理器
 * @author zxl
 * @date 2021/3/26 11:09
 */
@Slf4j
public class NettyLongClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 客户端连接活跃时，寻找数据发送
     * @param ctx 上下文
     * @throws Exception 异常
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] req = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(req);
        log.info("客户端处理器接收数据:{}",bytesToHex(req));
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
