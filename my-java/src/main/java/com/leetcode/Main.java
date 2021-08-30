package com.leetcode;

public class Main {

    /**
     * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。
     * 返回你可以获得的最大乘积。
     * eg:输入: 10
     * 输出: 36
     * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
     */
    public static void main(String[] args) {
        // write your code here
        int n = 2;
        System.out.println(new Main().integerBreak(n));
    }

    int integerBreak(int n) {
        
        int n3 = n / 3;
        int ns = 1;
        for(int i = 0; i < n3; i ++){
            ns *= 3;
        }
        int l = n - n3 * 3;
        if(n - ns < 3){
            l += 3;
            ns/=3;
        }
        return ns * l;
    }
}
