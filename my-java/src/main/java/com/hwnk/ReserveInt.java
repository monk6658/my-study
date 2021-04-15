package com.hwnk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 倒序输出
 * @author zxl
 * @date 2021/4/13 14:17
 */
public class ReserveInt {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {
            StringBuilder sb = new StringBuilder();
            for(int i = str.length() - 1; i >= 0; i--){
                sb.append(str.charAt(i));
            }
            System.out.println(sb.toString());
        }

    }

}
