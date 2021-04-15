package com.hwnk;

import java.util.Scanner;

/**
 * 字符出现个数
 * @author zxl
 * @date 2021/4/12 12:45
 */
public class CharCount {



    private static int charCount(String sourceStr, char targetChar){
        int count = 0;
        for(int i = 0; i < sourceStr.length(); i++){
            if(sourceStr.charAt(i) == targetChar){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
//        System.out.println((int) 'a');
//        System.out.println((int) 'A' + 32);
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNext()){
            sb.append(scanner.next());
        }
        System.out.println(sb.toString());

        int i = charCount(sb.substring(0,sb.length()-1).toUpperCase(), sb.substring(sb.length()-1).toUpperCase().charAt(0));
        System.out.println(i);

    }

}
