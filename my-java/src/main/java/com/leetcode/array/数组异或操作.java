package com.leetcode.array;
/**
 * 
 * @author zxl
 * @date 2021/5/7 7:58
 */
public class 数组异或操作 {




    public static void main(String[] args) {
        int n = 10;
        int start = 5;
        int num = xorOperation(n, start);
        System.out.println(num);
    }

    /*
    给你两个整数，n 和 start 。
    数组 nums 定义为：nums[i] = start + 2*i（下标从 0 开始）且 n == nums.length 。
    请返回 nums 中所有元素按位异或（XOR）后得到的结果。

    输入：n = 4, start = 3
    输出：8
    解释：数组 nums 为 [3, 5, 7, 9]，其中 (3 ^ 5 ^ 7 ^ 9) = 8.

     */
    public static int xorOperation(int n, int start) {
        int num = start;
        for(int i = 1; i < n; i++){
            num^=(start + 2*i);
        }
        return num;
    }


}
