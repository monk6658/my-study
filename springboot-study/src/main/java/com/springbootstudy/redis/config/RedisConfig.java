package com.springbootstudy.redis.config;


import com.springbootstudy.redis.service.RedisReceiverService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * Redis 配置
 * @author gg
 */
@Configuration
public class RedisConfig {

    /**
     * 获取redis对象
     * @param factory redis连接工厂
     * @return redis可操作对象
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(stringRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(stringRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }


    /**
     * 订阅服务
     * @param connectionFactory 连接工厂
     * @param listenerAdapter socket监听控制器
     * @param listenerNettyAdapter netty监听控制器
     * @return redis 信息订阅监听控制器
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListenerAdapter listenerAdapter,
                                                   MessageListenerAdapter listenerNettyAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //订阅多个频道
        container.addMessageListener(listenerAdapter, new PatternTopic("recv"));
        container.addMessageListener(listenerNettyAdapter, new PatternTopic(""));
        return container;
    }

    /**
     * 利用反射来创建监听到消息之后的执行方法（socket）
     * @param redisReceiver redis 接收者方法
     * @return 消息监听者适配器（对应上述，监听器配置）
     */
    @Bean
    public MessageListenerAdapter listenerAdapter(RedisReceiverService redisReceiver) {
        return new MessageListenerAdapter(redisReceiver, "receiveMessage");
    }







}
