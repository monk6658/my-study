package com.mythread;

/**
 * 测试
 * @author zxl
 * @date 2021/4/20 17:25
 */
public class TestVolatile {


    public static void main(String[] args) {

        TestVolatile testVolatile = new TestVolatile();

        String msg = String.valueOf(System.currentTimeMillis());
        new Thread(() -> {
            while (!testVolatile.start){
                testVolatile.setStart(msg);
            }
            System.out.println(testVolatile.start);
        }).start();
        new Thread(()->{
            try {
                Thread.sleep(3000);
                testVolatile.start = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }


    volatile boolean start;
    public void setStart(String msg) {
        System.out.println(start + " : " + msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
