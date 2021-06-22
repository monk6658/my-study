package com.rabbitmqava.workqueue;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmqava.util.RabbitMqUtil;

/**
 * 生产者测试(轮询模式消费，工作队列  生产者——queue——多消费者)
 * 仅比直连模式多了消费者,平均分配，一次消费完成，具体消费情况依据各消费者内部业务逻辑。
 * 存在消费2完事，消费1还在挨个执行
 * @author gg
 */
public class Provider {

    public static void main(String[] args) throws Exception {

        Connection connection = RabbitMqUtil.getConnection();

        Channel channel = connection.createChannel();
        channel.queueDeclare("hello", true, false, false, null);

        for (int i = 1; i <= 20; i++) {
            channel.basicPublish("","hello",null,("hello —— " + i).getBytes());
        }

        
        RabbitMqUtil.closeConnAndChannel(connection,channel);

    }

}
