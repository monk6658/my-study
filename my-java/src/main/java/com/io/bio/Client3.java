package com.io.bio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author: zxl
 * @create: 2024-02-18 15:05
 **/
public class Client3 {

    public static String SPLIT = "@@@";

    public static int FLAG_1 = 1; // 上线
    public static int FLAG_2 = 2; // 群发
    public static int FLAG_3 = 3; // 私发

    private static String nameStr = "";    
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        Socket socket = new Socket("127.0.0.1",9999);

        String name;
        System.out.print("请输入姓名: " );
        name = sc.nextLine();
        
        new Thread(()->{
            try {
                OutputStream os = socket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeInt(FLAG_1);
                dos.writeUTF(name);
                dos.flush();
                System.out.println(name + " 登录成功");
                while (true){
                    String s = sc.nextLine();
                    int flag = FLAG_2;
                    if(s.startsWith("@")){
                        String sname = s.substring(s.indexOf("@")+1, s.lastIndexOf("@"));
                        if(!nameStr.contains(sname)){
                            System.out.println("该人员不存在!!！");
                            continue;
                        }
                        flag = FLAG_3;
                    }
                    dos.writeInt(flag);
                    System.out.println("发送消息: " + s);
                    dos.writeUTF(s);
                    dos.flush();
                }
                
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
        
        
        
        new Thread(()->{
            try {
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                
                while (true){
                    int flag = dis.readInt();

                    if(flag == FLAG_1){
                        nameStr = dis.readUTF();
                    }
                    else if(flag == FLAG_2){
                        System.out.println("接收到消息: " + dis.readUTF());
                    }
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
        
  


   
        
    }
    
}
