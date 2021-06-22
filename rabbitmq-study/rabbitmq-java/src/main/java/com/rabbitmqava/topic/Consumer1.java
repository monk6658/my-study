package com.rabbitmqava.topic;

import com.rabbitmq.client.*;
import com.rabbitmqava.util.RabbitMqUtil;

import java.io.IOException;

/**
 * 消费者测试
 * @author gg
 */
public class Consumer1 {

    private final static String exchangeName = "topic";
    private final static String routingKey_1 = "user.insert";
    private final static String routingKey_2 = "topic.r";
    private final static String routingKey_3 = "user.insert.ad";

    public static void main(String[] args) throws IOException {

        Connection connection = RabbitMqUtil.getConnection();

        Channel channel = connection.createChannel();

        // 获得临时队列
        String queue = channel.queueDeclare().getQueue();

        // 绑定交换机及队列
        channel.queueBind(queue,exchangeName,"user.*");

        // 发布消息(持久化)
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1: " + new String(body));
            }
        });

    }


}
