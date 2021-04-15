package com.hwnk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.TreeMap;

/**
 * 合并键值对
 * @author zxl
 * @date 2021/4/12 17:38
 */
public class MergeKeyValues {

    /**
         输入
         4
         0 1
         0 2
         1 2
         3 4

         输出
         0 3
         1 2
         3 4
     */
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null){
            int count = Integer.parseInt(str);
            TreeMap<Integer,Integer> map = new TreeMap<>();
            for(int i = 0; i < count; i++){
                String tempNum = br.readLine();
                int num1 = Integer.parseInt(tempNum.substring(0,tempNum.indexOf(" ")));
                int num2 = Integer.parseInt(tempNum.substring(tempNum.indexOf(" ")+1));
                if(!Objects.isNull(map.get(num1))){
                    map.put(num1,map.get(num1) + num2);
                }else{
                    map.put(num1,num2);
                }
            }
            map.forEach((k,v)-> System.out.println(k + " " + v));
        }
    }

}
