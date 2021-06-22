package com.mynetty.nettystudy.bio.shortlink.client;


import com.sxm.common.util.SocketClientUtil;
import com.sxm.common.util.thread.MyThreadPoolExecutor;
import com.sxm.common.util.thread.MyThreadPoolUtil;

/**
 * bio 客户端
 * @author zxl
 * @date 2020/12/18 15:21
 */
public class BioClient {

    public static void main(String[] args) throws Exception {

        MyThreadPoolExecutor myThreadPoolExecutor = MyThreadPoolUtil.getMyThreadPoolExecutor();

        String ip = "127.0.0.1";
        String port = "6000";
        myThreadPoolExecutor.execute(() -> SocketClientUtil.send(ip,port,"测试数据一"));
        myThreadPoolExecutor.execute(() -> SocketClientUtil.send(ip,port,"测试数据二" + Thread.currentThread().getName()));
        myThreadPoolExecutor.execute(() -> SocketClientUtil.send(ip,port,"测试数据sn" + Thread.currentThread().getName()));
        myThreadPoolExecutor.shutdown();

    }




}
