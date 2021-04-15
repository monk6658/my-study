package com.hwnk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 十进制转二进制，并统计1的个数
 * @author zxl
 * @date 2021/4/13 16:15
 */
public class DToBCount1 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {
            int num = Integer.parseInt(str);
            int count = 0;
            while (num != 0){
                num &= (num - 1);
                count++;
            }
            System.out.println(count);
        }

    }

}
