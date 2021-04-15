package com.hwnk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 浮点数四舍不入
 * @author zxl
 * @date 2021/4/12 17:30
 */
public class FloatRounding {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null){
            int i = str.indexOf('.');
            int num = Integer.parseInt(str.substring(0,i));
            char c = str.charAt(i + 1);
            if(c - 48 >= 5){
                System.out.println(++num);
            }else {
                System.out.println(num);
            }
        }

    }

}
