package com.rabbitmqava.topic;


import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmqava.util.RabbitMqUtil;

import java.io.IOException;

/**
 * 动态路由模式（ 生产者——exchange——路由多临时queue——路由多消费者）
 * 仅比以上新增动态匹配 * 一个 # 一个或多个
 * @author gg
 */
public class Provider {

    private final static String exchangeName = "topic";
    private final static String routingKey_1 = "user.insert";
    private final static String routingKey_2 = "topic.r";
    private final static String routingKey_3 = "user.insert.ad";

    public static void main(String[] args) throws IOException {

        Connection connection = RabbitMqUtil.getConnection();

        Channel channel = connection.createChannel();

        // 绑定交换机
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC);

        // 发布消息()
        channel.basicPublish(exchangeName,routingKey_1, null,("动态路由模型" + routingKey_1).getBytes());

        // 发布消息()
        channel.basicPublish(exchangeName,routingKey_2, null,("动态路由模型" + routingKey_2).getBytes());

        // 发布消息()
        channel.basicPublish(exchangeName,routingKey_3, null,("动态路由模型" + routingKey_3).getBytes());

        RabbitMqUtil.closeConnAndChannel(connection,channel);
    }

}
