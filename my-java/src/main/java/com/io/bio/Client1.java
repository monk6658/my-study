package com.io.bio;

import java.io.*;
import java.net.Socket;

/**
 * @author: zxl
 * @create: 2024-02-18 15:05
 **/
public class Client1 {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1",9999);

        OutputStream os = socket.getOutputStream();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        
        bw.write("hello \n");
        bw.flush();
        
        bw.close();
        os.close();
        
    }
    
}
