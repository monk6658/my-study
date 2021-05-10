package com.leetcode.num;
/**
 * 
 * @author zxl
 * @date 2021/5/8 8:46
 */
public class 整数反转 {
    /*
    给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
    如果反转后整数超过 32 位的有符号整数的范围 [−2^31,  2^31 − 1] ，就返回 0。
    假设环境不允许存储 64 位整数（有符号或无符号）。

    输入：x = 120
    输出：21

    输入：x = 0
    输出：0

    输入：x = -123
    输出：-321
     */
    public static void main(String[] args) {

        System.out.println(reverse(901000));
    }

    public static int reverse(int x) {
        int rev = 0;
        while (x != 0){
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int d = x%10;
            x/=10;
            rev = rev*10+d;
        }
        return rev;
    }

}
