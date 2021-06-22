package com.sxm.common;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zxl
 * @date 2021/6/4 15:56
 */
@Slf4j
public class TestThread {

    public static void main(String[] args) throws InterruptedException {


        Thread thread = new Thread(() -> {
            log.info("线程一睡眠,{}",Thread.currentThread().getId());
            Thread.yield();
            Thread.interrupted();
            log.info("线程一启动,{}",Thread.currentThread().getId());
        });
        thread.setPriority(9);

        Thread thread1 = new Thread(() -> {
//            try {
                log.info("线程二睡眠,{}",Thread.currentThread().getId());
//                Thread thread2 = thread;
//                log.warn("测试:{}",thread2.getId());
//                thread2.join();
//                Thread.sleep(2000);
                log.info("线程二启动,{}",Thread.currentThread().getId());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        });
        thread1.setPriority(10);
        thread.start();
        thread1.start();

    }

}
