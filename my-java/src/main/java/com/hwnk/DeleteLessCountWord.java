package com.hwnk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 删除出现最少字符（多个字符出现次数一致都最少，都删除）
 * abcdd -> dd 字符串长度小于等于20个字节。
 * @author zxl
 * @date 2021/4/14 11:16
 */
public class DeleteLessCountWord {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {
            int length = str.length();
            Map<Character, Integer> map = new LinkedHashMap<>();

            for(int i = 0; i < length; i++){
                char c = str.charAt(i);
                // 1. 出现次数最少的字符串们
                map.put(c,map.get(c)!=null ? map.get(c) + 1 : 1);
            }

            int min = 40;
            for(Map.Entry<Character, Integer> temp : map.entrySet()){
                min = Math.min(min,temp.getValue());
            }
            StringBuilder sb = new StringBuilder();
            // 2. 去除
            for(int i = 0; i < length; i++){
                char c = str.charAt(i);
                if(min != map.get(c)){
                    sb.append(c);
                }
            }

            System.out.println(sb.toString());
        }
    }
}
