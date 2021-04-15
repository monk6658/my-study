package com.hwnk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 键盘密码
 * @author zxl
 * @date 2021/4/14 10:22
 */
public class KeyBoardPass {

    /**
     * 1. 大写字母则变成小写之后往后移一位
     * 2. 小写字母都变成对应的数字，数字和其他的符号都不做变换，
     *    1--1， abc--2, def--3, ghi--4, jkl--5, mno--6, pqrs--7, tuv--8 wxyz--9, 0--0
     */
    public static void main(String[] args) throws IOException {
        String resourceStr = "abcdefghijklmnopqrstuvwxyz";
        String targetStr =   "22233344455566677778889999";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {
            int length = str.length();
            char[] chars = new char[length];
            for(int i = 0; i < length; i++){
                char c = str.charAt(i);
                // 1. 大写变小写，加1
                if(c >= 'A' && c < 'Z'){
                    chars[i] = (char) (c + 33);
                }else if(c == 'Z'){
                    // 2. Z 对应 a
                    chars[i] = 'a';
                }else if(c >= 'a' && c <= 'z'){
                    // 3. 小写变数字
                    chars[i] = targetStr.charAt(resourceStr.indexOf(c));
                }else {
                    // 4. 其他字符不变
                    chars[i] = c;
                }
            }
            System.out.println(new String(chars));


        }


    }


}


//        System.out.println((int)'A');
//                System.out.println((int)'Z');
//
//                System.out.println((int)'a');
//                System.out.println((int)'z');
//
//                System.out.println((int)'0');
//                System.out.println((int)'9');
