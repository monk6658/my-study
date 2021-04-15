package com.hwnk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 倒序输出不重复整数
 * @author zxl
 * @date 2021/4/13 12:05
 */
public class ReserveDistinctInt {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {
            StringBuilder sb = new StringBuilder();
            for(int i = str.length() - 1; i >= 0; i--){

                char c = str.charAt(i);
                if(sb.indexOf(String.valueOf(c)) < 0){
                    sb.append(c);
                }
            }
            System.out.println(sb.toString());
        }
    }

}
