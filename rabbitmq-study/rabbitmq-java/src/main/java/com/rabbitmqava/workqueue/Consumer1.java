package com.rabbitmqava.workqueue;

import com.rabbitmq.client.*;
import com.rabbitmqava.util.RabbitMqUtil;

import java.io.IOException;

/**
 * 消费者测试
 * @author gg
 */
public class Consumer1 {


    public static void main(String[] args) throws IOException {

        Connection connection = RabbitMqUtil.getConnection();

        Channel channel = connection.createChannel();

        // 单次确认
        channel.basicQos(1);
        channel.queueDeclare("hello", true, false, false, null);

        // 发布消息(持久化)
        channel.basicConsume("hello",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者1:" + new String(body));

                // 消息确认机制，上面 basicQos，autoAck,及手动确认，保证能者多劳
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });

    }


}
