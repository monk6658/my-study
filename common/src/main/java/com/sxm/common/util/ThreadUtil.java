package com.sxm.common.util;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工具类
 * @author zxl
 * @date 2020/12/18 15:41
 */
public class ThreadUtil {

    public ThreadPoolExecutor getPoolExecutor(){
        ThreadFactory threadFactory = new CustomizableThreadFactory("demo-pool-");
        //Common Thread Pool
        return new ThreadPoolExecutor(5, 10,
                5, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(20), threadFactory, new ThreadPoolExecutor.AbortPolicy());
    }

}
