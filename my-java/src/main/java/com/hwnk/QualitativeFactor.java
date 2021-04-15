package com.hwnk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 质因数
 * @author zxl
 * @date 2021/4/12 16:51
 */
public class QualitativeFactor {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null){
            StringBuilder sb = new StringBuilder();
            long num = Long.parseLong(str);
            for(int i = 2; i <= Math.sqrt(num); i++){
                if(num % i == 0){
                    sb.append(i).append(" ");
                    num /= i;
                    i--;
                }
            }
            sb.append(num).append(" ");
            System.out.println(sb.toString());
        }

    }

}
