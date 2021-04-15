package com.leetcode.array;

/**
 * 一维数组区间和问题
 * @author zxl
 * @date 2021/3/1 8:40
 */
public class NumArray {

    /*** 常规算法 ****/
//    private int[] nums;
//
//    public NumArray(int[] nums) {
//        this.nums = nums;
//    }
//
//    public int sumRange(int i, int j) {
//        int sum = 0;
//        for(int a = i;a<=j;a++){
//            sum += nums[a];
//        }
//        return sum;
//    }

    /**** 前缀和算法 *****/
    private int[] sums;

    public NumArray(int[] nums) {
        sums = new int[nums.length+1];
        for(int i = 0; i < nums.length; i++){
            sums[i+1] = nums[i] + sums[i];
        }
    }

    public int sumRange(int i, int j) {
        return sums[j+1] - sums[i];
    }


    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        int i = numArray.sumRange(2, 5);
        System.out.println(i);
    }



}
