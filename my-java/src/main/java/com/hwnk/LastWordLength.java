package com.hwnk;

import java.util.Scanner;

/**
 * 最后一个单词长度
 * @author zxl
 * @date 2021/4/12 11:47
 */
public class LastWordLength {


    private static int lastWordLength(String word){
        //1. 空串返回0
        if(word == null || word == ""){
            return 0;
        }

        int count = 0;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(c != ' '){
                count++;
            }else{
                count = 0;
            }
        }

        return count;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (scan.hasNext()){
            sb.append(scan.next()).append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(lastWordLength(sb.toString()));

    }



}
