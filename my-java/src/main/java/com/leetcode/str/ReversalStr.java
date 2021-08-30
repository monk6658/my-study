package com.leetcode.str;

import java.util.Arrays;

/**
 * 字符串反转
 */
public class ReversalStr {

    public static void main(String[] args) {

        ReversalStr reversalStr = new ReversalStr();

        String str = "abcdefg";
        int k = 2;

        String result = reversalStr.reverseStr(str, k);
        System.out.println(result);

    }

    /**
     * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。
     *
     *     如果剩余字符少于 k 个，则将剩余字符全部反转。
     *     如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     *              
     *
     *     示例 1：
     *
     *     输入：s = "abcdefg", k = 2
     *     输出："bacdfeg"
     *     示例 2：
     *
     *     输入：s = "abcd", k = 2
     *     输出："bacd"
     */
    public String reverseStr(String s, int k) {

        char[] chars = s.toCharArray();
        int n = chars.length;
        for (int i = 0; i < n; i+=2*k ) {
            reverse(chars,i,Math.min(i+k,n)-1);
        }
        return new String(chars);
    }

    void reverse(char[] chars,int left, int right){
        while (left < right){
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }


}
