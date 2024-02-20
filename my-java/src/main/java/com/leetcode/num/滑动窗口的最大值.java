package com.leetcode.num;


import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class 滑动窗口的最大值 {

    /**
     * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
     * <p>
     * 示例:
     * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
     * 输出: [3,3,5,5,6,7]
     * 解释:
     * <p>
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     * 1 [3  -1  -3] 5  3  6  7       3
     * 1  3 [-1  -3  5] 3  6  7       5
     * 1  3  -1 [-3  5  3] 6  7       5
     * 1  3  -1  -3 [5  3  6] 7       6
     * 1  3  -1  -3  5 [3  6  7]      7
     */
    public static void main(String[] args) {

        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));


        int[] nums1 = {1};
        k = 1;
        System.out.println(Arrays.toString(maxSlidingWindow(nums1, k)));


    }

//    // 暴力解法
//    private static int[] maxSlidingWindow(int[] nums, int k){
//        if(nums == null || k <= 0){
//            return new int[0];
//        }
//        
//        int[] result = new int[nums.length - k + 1];
//        for (int i = 0; i < nums.length - k + 1; i++) {
//            int max = nums[i];
//            for (int j = i + 1; j < (i + k); j++) {
//                max = Math.max(nums[j],max);
//            }
//            result[i] = max;
//        }
//        
//        return  result;
//    }

//    // 记录结果, 再次暴力
//    private static int[] maxSlidingWindow(int[] nums, int k){
//        if(nums == null || k <= 0){
//            return new int[0];
//        }
//
//        int[] result = new int[nums.length - k + 1];
//        
//        // 初始化 需移除的值, 及最大值
//        int removeIndexValue = Integer.MIN_VALUE;
//        int max = removeIndexValue;
//        
//        for (int i = 0; i < nums.length - k + 1; i++) {
//            
//            // 移除值和最大值相等时, 循环找出新的最大值
//            if(removeIndexValue == max){
//                max = nums[i]; // 初始化最大值
//                for (int j = i + 1; j < (i + k); j++) {
//                    max = Math.max(nums[j],max);
//                }
//            }else{
//                // 最大值仍在下一次数组里时, 直接把最大值和新加入的值进行比较
//                max = Math.max(nums[i+k-1],max);
//            }
//            
//            result[i] = max; // 记录最大值
//            removeIndexValue = nums[i];  // 记录移除的值
//        }
//
//        return  result;
//    }

    // 优先队列 做大小排序
    private static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }

        int[] result = new int[nums.length - k + 1];
        Queue<Integer> queue = new PriorityQueue<>(result.length, (o1, o2) -> o2 - o1);
        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }

        // 从队列开头 移除第一组最大元素
        result[0] = queue.peek();
        int last = nums[0]; // 移除元素
        for (int i = k; i < nums.length; i++) {

            queue.remove(last);

            queue.offer(nums[i]);
            
            last = nums[i - k + 1];
            result[i - k + 1] = queue.peek();

        }
        return result;
    }


}
