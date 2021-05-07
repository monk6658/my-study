package com.sxm.common.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;

/**
 * 
 * @author zxl
 * @date 2021/4/23 17:49
 */
public class IpUtil {

    /**
     * 获取端口号
     * @param href 网址, ftp, http, nntp, ... 等等
     * @return 端口
     * @throws IOException 执行异常
     */
    public static int parsePort(String href) throws IOException {
        //java.net中存在的类
        URL url = new URL(href);
        // 端口号; 如果 href 中没有明确指定则为 -1
        int port = url.getPort();
        if (port < 0) {
            // 获取对应协议的默认端口号
            port = url.getDefaultPort();
        }
        return port;
    }

    /**
     * 获取Host部分
     * @param href 网址, ftp, http, nntp, ... 等等
     * @return 域名部分
     * @throws IOException 执行异常
     */
    public static String parseHost(String href) throws IOException {
        URL url = new URL(href);
        // 获取 host 部分
        return url.getHost();
    }


    /**
     * 根据域名(host)解析IP地址
     * @param host 域名
     * @return Ip
     * @throws IOException 执行异常
     */
    public static String parseIp(String host) throws IOException {
        // 根据域名查找IP地址
        InetAddress inetAddress = InetAddress.getByName(host);
        // IP 地址
        return inetAddress.getHostAddress();
    }


}
