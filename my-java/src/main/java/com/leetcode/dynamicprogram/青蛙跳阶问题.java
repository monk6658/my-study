package com.leetcode.dynamicprogram;

public class 青蛙跳阶问题 {


    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 10 级的台阶总共有多少种跳法。
     * @param args
     */
    public static void main(String[] args) {
        int n = 5;
        System.out.println(numWays(n));
        
    }

    /**
     * 暴力枚举
     */
//    private static int numWays(int n){
//        if(n <= 1){
//            return 1;
//        }
//        else if(n == 2){
//            return 2;
//        }
//        return go(n - 1) + go(n - 2);
//    }
    
    
//    private static int numWays(int n){
//        if(n<=1){
//            return 1;
//        }else if(n == 2){
//            return 2;
//        }
//        int[] num = new int[n];
//        num[0] = 1;
//        num[1] = 2;
//        for (int i = 2; i < num.length; i++) {
//            num[i] = (num[i-1] + num[i-2])%1000000007;;
//        }
//        return num[n-1];
//    }

    private static int numWays(int n){
        if(n<=1){
            return 1;
        }else if(n == 2){
            return 2;
        }
        int a = 1;
        int b = 2;
        int temp = 0;
        for(int i = 3; i <= n; i++){
            temp = (a + b);
            a = b;
            b = temp;
        }
        return temp;
    }
    
}
