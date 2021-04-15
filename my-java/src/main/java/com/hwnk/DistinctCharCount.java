package com.hwnk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 不重复字符串个数
 * @author zxl
 * @date 2021/4/13 13:56
 */
public class DistinctCharCount {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {
            StringBuilder sb = new StringBuilder();
            int count = 0;
            for(int i = 0; i < str.length(); i++){
                char c = str.charAt(i);
                if(sb.indexOf(String.valueOf(c)) < 0){
                    sb.append(c);
                    count++;
                }

            }
            System.out.println(count);
        }
    }
}
