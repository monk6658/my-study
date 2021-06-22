package com.rabbitmqspringboot.hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author zxl
 * @date 2021/6/9 17:46
 */
@Slf4j
@Component // 持久化  非独占   自动删除
@RabbitListener(queuesToDeclare = @Queue(value = "hello1",durable = "true",autoDelete = "true"))
public class HelloConsumer {

    @RabbitHandler
    public void receive(String msg){
        log.info("接收消息:{}",msg);
    }

}
