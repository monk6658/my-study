package com.mynetty.nettystudy.zerocopy.old;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 传统io复制客户端
 * 注：即使是传统方式，使用buffer缓存技术后，效率仍有很大的提升
 * @author zxl
 * @date 2020/12/28 11:34
 */
public class OidCopyClient {


    public static void main(String[] args) throws Exception {

        //1. 连接服务端
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1",5001));
        socket.setTcpNoDelay(true);
        //2. 通过流获取文件信息
        String fileName = "E:\\tools\\apache-jmeter-5.1.1.zip";
        InputStream is = new FileInputStream(new File(fileName));
        BufferedInputStream bis = new BufferedInputStream(is);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        BufferedOutputStream bos = new BufferedOutputStream(dos);

        long startTime = System.currentTimeMillis();
        //3. 通过socket进行发送，并计算时间
        byte[] bytes = new byte[1024];
        int readCount;
        int total = 0;
        while ((readCount = bis.read(bytes)) > 0){
            total += readCount;
            bos.write(bytes);
        }
        System.out.println("字节:" + total + ", 执行时间:" + (System.currentTimeMillis() - startTime));

        bos.close();
        dos.close();
        bis.close();
        is.close();
        socket.close();
    }

}
