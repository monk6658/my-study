package com.leetcode.str;

//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母组成 
// 
//
// Related Topics 字符串 动态规划 👍 5961 👎 0

public class 最长回文子串 {

    public static void main(String[] args) {

        System.out.println(longestPalindrome("aaaaa"));

    }


    public static String longestPalindrome(String s) {

        int length = s.length();
        int[][] dp = new int[length][length];
        int start = 0, max = 1; // 默认一个字符为回文
        
        for (int i = 0; i < length; i++) {
            dp[i][i] = 1;
            if(i < length - 1 && s.charAt(i) == s.charAt(i+1)){
                dp[i][i+1] = 1;
                start = i;
                max = 2;
            }
        }
        
        for (int l = 3; l <= length; l++) { // 字符串长度
            for (int i = 0; i <= length - l; i++) {
                int j = l + i - 1;
                if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1] == 1){
                    dp[i][j] = 1;
                    start = i;
                    max = l;
                }            
            }
        }

        return s.substring(start,start + max);

    }

    
    
}
