package com.leetcode.dynamicprogram;

public class 最长回文字符串 {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("cbbdd"));
    }


    /**
     * 给一个字符串s，找到s中最长的回文子串
     * 
     * 示例1： 
     * babad -- > bab   or  aba
     * 
     * cbbd  -- > bb
     * 
     */
    private static String longestPalindrome(String s){
        int longest = 1;
        int start=0;
        char[] chars = s.toCharArray();
        int length = s.length();
        int[][] dp = new int[length][length];
        for(int i = 0; i < length; i++){
            // 计算一个字符得
            dp[i][i] = 1;
            if(i < length - 1){
                // 计算两个字符 是否是回文
                if(chars[i] == chars[i+1]){ 
                    dp[i][i+1] = 1;
                    start=i;
                    longest=2;
                }
            }
        }
        
        // 计算三个及全部
        for(int l = 3; l <= length; l++){
            for(int i = 0; i + l - 1 < length; i++){
                int j=l+i-1;//终点
                if (chars[i] == chars[j] && dp[i+1][j-1]==1){
                    dp[i][j] = 1;
                    start=i;
                    longest = l;
                }
            }
        }
        
        return s.substring(start,longest);
    }
    
    
}
