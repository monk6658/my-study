package com.hwnk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 校验IP和子关掩码，并判断其类型
 * @author zxl
 * @date 2021/4/13 17:39
 */
public class JudgeIpAndMask {

    /**
     * 请解析IP地址和对应的掩码，进行分类识别。要求按照A/B/C/D/E类地址归类，不合法的地址和掩码单独归类。
     *
     * 所有的IP地址划分为 A,B,C,D,E五类
     * A类地址1.0.0.0~126.255.255.255;
     * B类地址128.0.0.0~191.255.255.255;
     * C类地址192.0.0.0~223.255.255.255;
     * D类地址224.0.0.0~239.255.255.255；
     * E类地址240.0.0.0~255.255.255.255
     *
     * 私网IP范围是：
     * 10.0.0.0～10.255.255.255
     * 172.16.0.0～172.31.255.255
     * 192.168.0.0～192.168.255.255
     *
     * 子网掩码为二进制下前面是连续的1，然后全是0。（例如：255.255.255.32就是一个非法的掩码）
     * 注意二进制下全是1或者全是0均为非法
     *
     * 注意：
     * 1. 类似于【0.*.*.*】和【127.*.*.*】的IP地址不属于上述输入的任意一类，也不属于不合法ip地址，计数时可以忽略
     * 2. 私有IP地址和A,B,C,D,E类地址是不冲突的
     *
     * 统计A、B、C、D、E、错误IP地址或错误掩码、私有IP的个数，之间以空格隔开。
     */
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        int[] result = new int[7];
        while ((str = br.readLine()) != null) {

            String tempIp = str.substring(0, str.indexOf("~"));
            String tempMask = str.substring(str.indexOf("~") + 1);
            // 1. IP 判断
            String[] splitIp = tempIp.split(".");
            if(splitIp[0] == "0" || splitIp[0] == "127"){
                continue;
            }

            // 2. 子关掩码判断



        }




    }

}
