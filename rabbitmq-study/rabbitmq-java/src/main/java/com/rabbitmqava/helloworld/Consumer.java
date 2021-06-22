package com.rabbitmqava.helloworld;

import com.rabbitmq.client.*;
import com.rabbitmqava.util.RabbitMqUtil;

import java.io.IOException;

/**
 * 消费者测试
 * @author gg
 */
public class Consumer {


    public static void main(String[] args) throws IOException {

        Connection connection = RabbitMqUtil.getConnection();

        Channel channel = connection.createChannel();
        channel.queueDeclare("hello", true, false, false, null);

        // 发布消息(持久化)
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });

    }


}
