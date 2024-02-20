package com.io.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: zxl
 * @create: 2024-02-18 15:05
 **/
public class Server1 {


    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9999);

        System.out.println("启动成功.......");

        Socket socket = serverSocket.accept();

        InputStream is = socket.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String msg;
        while ((msg = br.readLine()) != null){
            System.out.println("接收信息：" + msg);
        }
        
//        br.close();
//        is.close();
        

    }
    
}
