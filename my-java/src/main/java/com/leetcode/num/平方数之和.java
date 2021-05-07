package com.leetcode.num;
/**
 * 平方数之和
 * @author zxl
 * @date 2021/4/28 10:43
 */
public class 平方数之和 {

    /*
        输入：c = 5
        输出：true
        解释：1 * 1 + 2 * 2 = 5
     */
    public static boolean judgeSquareSum(int c) {
        int[] t = new int[c];
        if(c == 0){
            return true;
        }

        for(int i = 0; i <= c && i*i <= c; i++){
            t[i] = i*i;

            for(int j = 0; j <= i; j++){



            }




        }

        for(int i = 0; i <= c; i++){




            if( i * i == c){
                return true;
            }

            int temp = c - i;

            if( i == c / i){


            }
        }







        return true;
    }


    public static void main(String[] args) {
        System.out.println(judgeSquareSum(5));
    }

}
