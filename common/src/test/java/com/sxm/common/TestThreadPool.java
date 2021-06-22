package com.sxm.common;

import com.sxm.common.util.thread.MyThreadPoolUtil;

/**
 * 线程池相关测试
 * @author zxl
 * @date 2021/6/15 17:36
 */
public class TestThreadPool {

    public static void main(String[] args) {

        MyThreadPoolUtil.getMyThreadPoolExecutor().execute(new T());


    }

}
