package com.sxm.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * spring 公共bean创建
 * @author zxl
 * @date 2021/4/9 14:49
 */
@Component
public class BeanConfig {

    @Value("${pool.threadNamePrefix}")
    private String threadNamePrefix;

    @Value("${pool.coreSize}")
    private int coreSize;

    @Value("${pool.maxSize}")
    private int maxSize;

    @Value("${pool.keepAliveTime}")
    private Long keepAliveTime;

    @Value("${pool.capacity}")
    private int capacity;

    public ExecutorService getExecutorService(){
        ThreadFactory threadFactory = new CustomizableThreadFactory(threadNamePrefix);
        //Common Thread Pool
        return new ThreadPoolExecutor(coreSize, maxSize,
                keepAliveTime, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(capacity), threadFactory, new ThreadPoolExecutor.AbortPolicy());
    }

}
