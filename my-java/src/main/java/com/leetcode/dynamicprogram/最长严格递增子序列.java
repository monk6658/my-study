package com.leetcode.dynamicprogram;

public class 最长严格递增子序列 {
    /**
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 输入：nums = [10,9,2,5,3,7,101,18]
     * 输出：4
     * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
     * 
     * 输入：nums = [0,1,0,3,2,3]
     * 输出：4
     */
    public static void main(String[] args) {
//        int[] nums = {10,9,2,5,3,7,101,18};
        int[] nums = {0,1,0,3,2,3};
        System.out.println(lengthOfLIS(nums));
    }

    
    private static int lengthOfLIS(int[] nums){
        
        if(nums == null || nums.length == 1){
            return 1;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for(int i = 1; i < nums.length; i++){
            dp[i] = 1;
            for(int j = i - 1; j >= 0; j--){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            max = Math.max(max,dp[i]);
        }
     
        return max;
    }
}
