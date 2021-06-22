package com.rabbitmqava.routing;


import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmqava.util.RabbitMqUtil;

import java.io.IOException;

/**
 * 路由模式（ 生产者——exchange——路由多临时queue——路由多消费者）
 * @author gg
 */
public class Provider {

    private final static String exchangeName = "rout";
    private final static String routingKey_1 = "routingKey_1";
    private final static String routingKey_2 = "routingKey_2";

    public static void main(String[] args) throws IOException {

        Connection connection = RabbitMqUtil.getConnection();

        Channel channel = connection.createChannel();

        // 绑定交换机
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT);

        // 发布消息()
        channel.basicPublish(exchangeName,routingKey_1, null,("hello——PERSISTENT" + routingKey_1).getBytes());

        // 发布消息()
        channel.basicPublish(exchangeName,routingKey_2, null,("hello——PERSISTENT" + routingKey_2).getBytes());


        RabbitMqUtil.closeConnAndChannel(connection,channel);
    }

}
