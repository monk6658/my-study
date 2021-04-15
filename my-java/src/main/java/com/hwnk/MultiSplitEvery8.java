package com.hwnk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 多组数据，8个字节进行拆分，其余补0
 * @author zxl
 * @date 2021/4/12 15:21
 */
public class MultiSplitEvery8 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        while((str = br.readLine()) != null){
            int length = str.length();
            int i;
            for(i = 0; i < length/8; i++){
                System.out.println(str.substring(i*8,i*8+8));
            }
            int mod = length % 8;
            if(mod != 0){
                System.out.println(str.substring(i == 0 ? 0 : i*8) + "00000000".substring(0,8 - mod));
            }
        }
    }

}
