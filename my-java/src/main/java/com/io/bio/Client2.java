package com.io.bio;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author: zxl
 * @create: 2024-02-18 15:05
 **/
public class Client2 {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1",9999);

        OutputStream os = socket.getOutputStream();


        PrintStream ps = new PrintStream(os);
        Scanner sc = new Scanner(System.in);
        while (true){

            String s = sc.nextLine();
            ps.println(s);
            ps.flush();
            
            
        }
   
        
    }
    
}
