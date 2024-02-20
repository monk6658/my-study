package com.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: zxl
 * @create: 2024-02-18 15:05
 **/
public class Server2 {


    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9999);

        System.out.println("启动成功.......");

        while (true){

            Socket socket = serverSocket.accept();

            new Thread(()->{
                try {
                    InputStream is = socket.getInputStream();

                    BufferedReader br = new BufferedReader(new InputStreamReader(is));

                    String msg;
                    while ((msg = br.readLine()) != null){
                        System.out.println( Thread.currentThread().getName() + " 接收信息：" + msg);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                
            }).start();
            
            
        }
        
      
        
//        br.close();
//        is.close();
        

    }
    
}
