package com.rabbitmqava.fanout;


import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmqava.util.RabbitMqUtil;

import java.io.IOException;

/**
 * 广播（ 生产者——exchange——多临时queue——多消费者）
 * @author gg
 */
public class Provider {

    private final static String exchangeName = "logs";

    public static void main(String[] args) throws IOException {

        Connection connection = RabbitMqUtil.getConnection();

        Channel channel = connection.createChannel();

        // 绑定交换机
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT);

        // 发布消息()
        channel.basicPublish(exchangeName,"", null,"hello——PERSISTENT".getBytes());

        RabbitMqUtil.closeConnAndChannel(connection,channel);
    }

}
