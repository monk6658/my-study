package com.mynetty.nettystudy.bio.shortlink.server;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * bio 服务端
 * @author zxl
 * @date 2020/12/18 15:14
 */
public class BioServer {

    public static void main(String[] args) throws IOException {
        ThreadFactory threadFactory = new CustomizableThreadFactory("demo-pool-%d");
        //Common Thread Pool
        ExecutorService pool = new ThreadPoolExecutor(5, 10,
                5, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(20), threadFactory, new ThreadPoolExecutor.AbortPolicy());

        final ServerSocket serverSocket = new ServerSocket(6000);
        while (true){
            System.out.println("主线程阻塞等待....");
            System.out.println("线程信息：" + Thread.currentThread().getId() + " " + Thread.currentThread().getName());
            Socket socket = serverSocket.accept();
            pool.execute(()-> {
                try {
                    handler(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    /**
     * 业务处理
     * @param socket socket
     * @throws IOException 异常
     */
    private static void handler(Socket socket) throws IOException {
        System.out.println("子线程信息：" + Thread.currentThread().getId() + " " + Thread.currentThread().getName());
        InputStream is = socket.getInputStream();
        byte[] data = new byte[1024];
        while (true){
            System.out.println("数据读取阻塞....");
            int read = is.read(data);
            if(read != -1){
                System.out.println(new String(data,0,read));
            }else{
                break;
            }
        }
        System.out.println("线程关闭...");
        is.close();
    }


}
