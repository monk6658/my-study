package com.io.bio;

import org.springframework.util.StringUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * @author: zxl
 * @create: 2024-02-18 15:05
 **/
public class Server3 {

    private static Map<Socket,String> socketList = new HashMap<>();
    
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("启动成功.......");
        while (true){
            Socket socket = serverSocket.accept();
            new Thread(()->{
                try {
                    DataInputStream dis = new DataInputStream(socket.getInputStream());

                    while (true){

                        int flag = dis.readInt();
                        String msg = dis.readUTF();
                        System.out.println("收到信息:" + msg);
                        if(Client3.FLAG_1 == flag){
                            socketList.put(socket,msg);
                            msg = String.join(Client3.SPLIT,socketList.values());
                        }
                        if(!StringUtils.hasLength(msg)){
                            continue;
                        }
                        sendAllMsg(flag,msg);
                    }
                    
                }catch (Exception e){
                    System.out.println(socketList.get(socket) + " 已下线");
                    
                    socketList.remove(socket);
                    
                    try {
                        sendAllMsg(Client3.FLAG_1,socketList.get(socket));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        }
    }
    
    
    
    
    public static void sendAllMsg(int flag,String msg) throws IOException {

        if(Client3.FLAG_3 == flag){
            String name = msg.substring(msg.indexOf("@")+1, msg.lastIndexOf("@"));
            for (Socket socket : socketList.keySet()) {
                if(Objects.equals(name,socketList.get(socket))) {
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    dos.writeInt(Client3.FLAG_2);
                    dos.writeUTF(msg);
                    dos.flush();
                }
            }
        }else{
            for (Socket socket : socketList.keySet()) {
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeInt(flag);
                dos.writeUTF(msg);
                dos.flush();
            }
        }
        
    }
}
