package com.hwnk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 最优购买，动态规划问题
 * @author zxl
 * @date 2021/4/13 16:34
 */
public class BestBuyDynamicPlan {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {

            int maxAmt = Integer.parseInt(str.substring(0, str.indexOf(" ")));
            int count = Integer.parseInt(str.substring(str.indexOf(" ") + 1));
            int[][] product = new int[count][3];
            for(int i = 0; i < count; i++){
                String temp = br.readLine();
                product[i][0] = Integer.parseInt(temp.substring(0,temp.indexOf(" ")));
                product[i][1] = Integer.parseInt(temp.substring(temp.indexOf(" ")+1,temp.lastIndexOf(" ")));
                product[i][2] = Integer.parseInt(temp.substring(temp.lastIndexOf(" ")+1));
            }

            int sum = 0;
            int minSum = product[0][0];
            // 1. 查出最小金额索引、
            for(int i = 0; i < count; i++){
                minSum = Math.min(minSum, product[i][0]);

            }

            for(int i = 0; i < count; i++){
                System.out.println(Arrays.toString(product[i]));
            }


        }


    }

}
