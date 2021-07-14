package com.springbootstudy.redis.service;

/**
 * redis 监听
 * @author zxl
 * @date 2021/3/9 19:32
 */
public interface RedisReceiverService {

    /**
     * 接收socket 消息
     * @param message 消息
     */
    void receiveMessage(String message);

}
