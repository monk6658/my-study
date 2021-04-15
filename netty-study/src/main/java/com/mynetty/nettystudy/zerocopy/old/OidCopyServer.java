package com.mynetty.nettystudy.zerocopy.old;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 传统io拷贝
 * @author zxl
 * @date 2020/12/28 11:25
 */
public class OidCopyServer {

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(5001);
        while (true){
            Socket socket = serverSocket.accept();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            BufferedInputStream bis = new BufferedInputStream(dis);

            File file = new File("D://c.zip");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            byte[] bytes = new byte[8*1024];
            int read;
            int total = 0;
            while ((read = bis.read(bytes)) > 0){
                total += read;
                bos.write(bytes);
            }

            System.out.println("总字节数：" + total);
            bos.close();
            fos.close();
            bis.close();
            dis.close();

        }

    }

}
