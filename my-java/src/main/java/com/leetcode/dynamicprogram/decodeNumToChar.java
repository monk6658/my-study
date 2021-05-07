package com.leetcode.dynamicprogram;
/**
 * 数字解码为字符串
 * @author zxl
 * @date 2021/4/21 15:41
 */
public class decodeNumToChar {


    public static void main(String[] args) {
        String s = "12";
//        s = "12137";    // 5
//        s = "06";       // 0

//        System.out.println(numDecodings(s));
//        System.out.println(numDecodings2(s));


    }


    /**
     *
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     *
     * 输入：s = "12"
     * 输出：2
     * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
     *
     * 输入：s = "226"
     * 输出：3
     * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
     *
     * 输入：s = "06"
     * 输出：0
     * 解释："06" 不能映射到 "F" ，因为字符串含有前导 0（"6" 和 "06" 在映射中并不等价）。
     */
    public static int numDecodings(String s) {
        int length = s.length();

        int f[] = new int[length + 1];
        f[0] = 1;

        for(int i = 1; i <= length ; i ++){

            int a = s.charAt(i - 1) - '0';
            // 单独形成
            if(1 <= a && a <= 9){
                f[i] = f[i-1];
            }

            // 两个数形成
            if(i > 1 && s.charAt(i-2) != '0' && ((s.charAt(i-2) - '0')*10 + a) <= 26){
                f[i] += f[i-2];
            }
        }

        return f[length];
    }

    /**
     * 加强版，通过三个变量进行状态转移
     * @param s 字符串
     * @return 个数
     */
    public static int numDecodings2(String s) {
        int length = s.length();
        // i - 2    i - 1   i
        int pre = 0, cur = 1,num = 0;
        for(int i = 1; i <= length ; i ++){
            // 单独形成
            if(s.charAt(i - 1) != '0'){
                num = cur;
            }

            // 两个数形成
            if(i > 1 && s.charAt(i-2) != '0' && ((s.charAt(i-2) - '0')*10 + (s.charAt(i - 1) - '0')) <= 26){
                num += pre;
            }

            pre = cur;
            cur = num;
        }

        return num;
    }

}
