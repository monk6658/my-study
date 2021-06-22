package com.sxm.common.util.thread;


import lombok.NoArgsConstructor;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 线程池创建工具类
 * @author zxl
 * @date 2021/5/24 16:15
 */
@NoArgsConstructor
public class MyThreadPoolUtil {

    /*** 预防指令重排 致使初始化问题 */
    private volatile static MyThreadPoolExecutor myThreadPoolExecutor = null;

    /**
     * 使用双检索单例形式创建线程池对象
     * @return 自定义线程池信息
     */
    public static MyThreadPoolExecutor getMyThreadPoolExecutor(String namePreFix,int keepAliveTime,int maxPoolSize){
        if(myThreadPoolExecutor == null){
            synchronized (MyThreadPoolUtil.class){
                if(myThreadPoolExecutor == null) {
                    CustomizableThreadFactory customizableThreadFactory = new CustomizableThreadFactory(namePreFix);
                    int corePoolSize = Runtime.getRuntime().availableProcessors() * 2;
                    myThreadPoolExecutor = new MyThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), customizableThreadFactory);
                    // 超时线程自动回收
                    myThreadPoolExecutor.allowCoreThreadTimeOut(true);
                }
            }
        }
        return myThreadPoolExecutor;
    }

    /**
     * 获取默认线程池配置
     * @return 自定义线程池
     */
    public static MyThreadPoolExecutor getMyThreadPoolExecutor(){
        return getMyThreadPoolExecutor("pool-",6000,Runtime.getRuntime().availableProcessors()*4);
    }

}
