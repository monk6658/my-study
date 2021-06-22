package com.rabbitmqava.helloworld;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.rabbitmqava.util.RabbitMqUtil;

import java.io.IOException;

/**
 * 生产者测试（直连模式  生产者——queue——消费者）
 * @author gg
 */
public class Provider {

    public static void main(String[] args) throws IOException {

        Connection connection = RabbitMqUtil.getConnection();

        Channel channel = connection.createChannel();
        /*
        1.队列名称，不存在自动创建
        2.队列持久化特性 true 持久化，rabbitmq停止之后保存在磁盘(队列和消息一起持久化，重启才不会数据丢失)
        3.是否独占队列
        4.消费完成之后是否自动删除
        5.额外参数
         */
        channel.queueDeclare("hello", true, false, false, null);

        // 发布消息(持久化)
        channel.basicPublish("","hello", MessageProperties.PERSISTENT_TEXT_PLAIN,"hello——PERSISTENT".getBytes());

        channel.basicPublish("","hello",null,"hello —— 01".getBytes());

        RabbitMqUtil.closeConnAndChannel(connection,channel);
    }

}
