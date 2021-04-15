package com.hwnk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 逆序生产字符串，通过空格隔开
 * @author zxl
 * @date 2021/4/13 14:40
 */
public class ReserveCharBySpace {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {
            char[] chars = new char[str.length()];
            int j = 0;
            int tempWordCount = 0;
            for(int i = str.length() - 1; i >= 0; i--){
                if(str.charAt(i) != ' '){
                    tempWordCount++;
                }
                if(str.charAt(i) == ' '){
                    int k = i;
                    while (tempWordCount > 0){
                        chars[j] = str.charAt(++k);
                        j++;
                        tempWordCount--;
                    }
                    if(str.charAt(i) == ' '){
                        chars[j] = ' ';
                        j++;
                    }
                }
                if(i == 0){
                    int k = i;
                    while (tempWordCount > 0){
                        chars[j] = str.charAt(k++);
                        j++;
                        tempWordCount--;
                    }
                }
            }
            //Ia am a boy
            System.out.println(new String(chars));
        }
    }

}
