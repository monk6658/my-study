package com.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 最长严格递增子序列
 * @author zxl
 * @date 2021/3/8 16:05
 */
public class 最长严格递增子序列 {
    /*
    动态规划，重复使用已知结果
     */
    /**
     * 最长严格递增子序列
     * @param nums 数组
     * @return 长度
     */
    public static int lengthOfLIS(int[] nums) {
        int l = nums.length;
        if(l == 0){
            return 0;
        }

        int[] node = new int[l];
        node[0] = 1;
        int num = 1;
        for(int i = 1; i < l; i++){
            node[i] = 1;
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    node[i] = Math.max(node[i], node[j] + 1);
                }
            }
            num = Math.max(num, node[i]);
        }
        return num;

    }

    public static int lengthOfLIS1(int[] nums) {
        int l = nums.length;
        if(l == 0){
            return 0;
        }

        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for(int i = 1; i < l; i++){

            if(list.get(list.size()-1) < nums[i]){
                list.add(nums[i]);
            }else {
                // 找到当前最小值，替换
                int index = binarySearch(list, nums[i]);
                list.set(index,nums[i]);
            }

        }
        return list.size();

    }


    static int binarySearch(List<Integer> list, int nums){

        int low = 0,high = list.size() - 1;

        while (low < high){
            int mid = (high - low)/2 + low;
            if(list.get(mid) >= nums){
                high = mid;
            }else {
                low = mid + 1;
            }
        }
        return high;

    }








    public static void main(String[] args) {
        int[] s = {10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS1(s));
    }


}
