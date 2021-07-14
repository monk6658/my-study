package com.springbootstudy.redis.service.impl;


import com.springbootstudy.redis.service.RedisReceiverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 监听实现
 * @author zxl
 * @date 2021/3/9 19:33
 */
@Slf4j
@Service
public class RedisReceiverServiceImpl implements RedisReceiverService {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void receiveMessage(String message) {
        log.info("监听数据{}",message);
    }

    @PostConstruct
    public void t(){
        log.info("发送完成");
        redisTemplate.convertAndSend("recv","test");
    }

}
