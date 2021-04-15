package com.hwnk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 空瓶换水问题（可借用）
 * @author zxl
 * @date 2021/4/14 10:58
 */
public class ChangeWaterByEmptyBottle {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {
            System.out.println(str);
            // 空瓶个数
            int emptyBottleCount = Integer.parseInt(str);
            if(emptyBottleCount == 0){
                return;
            }
            int allCount = 0;
            while(emptyBottleCount > 1){
                int tempWater = emptyBottleCount/3;
                if(tempWater >= 1){
                    allCount += tempWater;
                    emptyBottleCount = emptyBottleCount%3 + tempWater;
                    continue;
                }

                // 2 瓶之后借水
                allCount++;
                emptyBottleCount = 0;
            }
            System.out.println(allCount);
        }
    }

}
