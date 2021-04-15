package com.hwnk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 16进制转换为十进制
 * @author zxl
 * @date 2021/4/12 16:14
 */
public class HexToDec {

    public static void main(String[] args) throws IOException {

        System.out.println((int)'A');
        System.out.println((int)'a');

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null){
            int length = str.length();
            int a = 0;
            int sum = 0;
            for(int i = length - 1; i >= 2; i--){
                char c = str.charAt(i);
                sum += (c > 64 ? (c - 55) : (c - 48)) * Math.pow(16,a);
                a++;
            }
            System.out.println(sum);
        }
    }

}
