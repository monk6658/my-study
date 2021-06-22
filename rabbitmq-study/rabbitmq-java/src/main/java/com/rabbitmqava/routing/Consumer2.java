package com.rabbitmqava.routing;

import com.rabbitmq.client.*;
import com.rabbitmqava.util.RabbitMqUtil;

import java.io.IOException;

/**
 * 消费者测试
 * @author gg
 */
public class Consumer2 {

    private final static String exchangeName = "rout";
    private final static String routingKey_1 = "routingKey_1";
    private final static String routingKey_2 = "routingKey_2";

    public static void main(String[] args) throws IOException {

        Connection connection = RabbitMqUtil.getConnection();

        Channel channel = connection.createChannel();

        // 获得临时队列
        String queue = channel.queueDeclare().getQueue();

        // 绑定交换机及队列
        channel.queueBind(queue,exchangeName,routingKey_2);

        // 发布消息(持久化)
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2: " + new String(body));
            }
        });

    }


}
