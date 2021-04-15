package com.sxm.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * socket工具类
 * @author zxl
 * @date 2020/12/18 16:00
 */
public class SocketClientUtil {

    /**
     * 发数据返回字符串
     * @param ip ip
     * @param port 端口
     * @param data 发送数据
     * @return 结果
     */
    public static String send(String ip,String port,String data){
        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(ip,Integer.parseInt(port)),3000);
            socket.setSoTimeout(3000);
            socket.setTcpNoDelay(true);
            is = socket.getInputStream();
            os = socket.getOutputStream();
            os.write(data.getBytes("UTF-8"));
            os.flush();
            StringBuilder sb = new StringBuilder();
            byte[] res = new byte[1024];
            while (true){
                int read = is.read(res);
                if(read != -1){
                    sb.append(new String(res,0,read));
                }else{
                    break;
                }
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(os != null){
                    os.close();
                }
                if(is != null){
                    is.close();
                }
                if(socket != null){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }


}
