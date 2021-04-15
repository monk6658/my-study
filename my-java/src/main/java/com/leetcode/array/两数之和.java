package com.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author zxl
 * @date 2021/3/9 11:27
 */
public class 两数之和 {

    /**
     * 暴利枚举
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    /**
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
            int i1 = list.indexOf(target - nums[i]);
            if(i1 >= 0 && i1 != i){
                return new int[]{i,i1};
            }
        }
        return null;

    }

    public static void main(String[] args) {
        int[] ints = twoSum1(new int[]{3, 2, 4}, 6);
        System.out.println(Arrays.toString(ints));
    }

}
