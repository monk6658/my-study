package com.mynetty.nettystudy.bio.shortlink.client;


import com.sxm.common.util.SocketClientUtil;
import com.sxm.common.util.ThreadUtil;

import java.util.concurrent.ExecutorService;

/**
 * bio 客户端
 * @author zxl
 * @date 2020/12/18 15:21
 */
public class BioClient {

    public static void main(String[] args) throws Exception {

        ThreadUtil threadUtil = new ThreadUtil();

        ExecutorService executorService = threadUtil.getPoolExecutor();

        String ip = "127.0.0.1";
        String port = "6000";
        executorService.execute(() -> SocketClientUtil.send(ip,port,"测试数据一"));
        executorService.execute(() -> SocketClientUtil.send(ip,port,"测试数据二" + Thread.currentThread().getName()));
        executorService.execute(() -> SocketClientUtil.send(ip,port,"测试数据sn" + Thread.currentThread().getName()));
        executorService.shutdown();

    }




}
