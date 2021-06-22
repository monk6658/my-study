package com.rabbitmqava.workqueue;

import com.rabbitmq.client.*;
import com.rabbitmqava.util.RabbitMqUtil;

import java.io.IOException;

/**
 * 消费者测试
 * @author gg
 */
public class Consumer2 {


    public static void main(String[] args) throws IOException {

        Connection connection = RabbitMqUtil.getConnection();

        Channel channel = connection.createChannel();

        channel.basicQos(1);
        channel.queueDeclare("hello", true, false, false, null);

        // 发布消息(持久化)
        channel.basicConsume("hello",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2:" + new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });

    }


}
