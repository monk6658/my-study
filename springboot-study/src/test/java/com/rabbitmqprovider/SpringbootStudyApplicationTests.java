package com.rabbitmqprovider;

import com.springbootstudy.SpringbootStudyApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest(classes= SpringbootStudyApplication.class)
class SpringbootStudyApplicationTests {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void contextLoads() {

        redisTemplate.convertAndSend("recv","asda");
    }

}
