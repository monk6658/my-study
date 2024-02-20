package com.leetcode.array;
//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。 
//
// 算法的时间复杂度应该为 O(log (m+n)) 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -10⁶ <= nums1[i], nums2[i] <= 10⁶ 
// 
//
// Related Topics 数组 二分查找 分治 👍 6362 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
public class 寻找两个正序数组的中位数4 {


    public static void main(String[] args) {
        int[] n1 = {3,5,6,7}, n2 = {1,5,9,10};
        n1 = new int[]{1,2};
        n2 = new int[]{3,4};
        
        System.out.println(findMedianSortedArrays(n1,n2));
        
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int i = 0,j = 0;
        int sum = n1 + n2;
        int[] temp = new int[sum/2 + 1];
        int k = 0;

        while (k < temp.length){
            if(i < n1 && j < n2 && nums1[i] > nums2[j]){
                temp[k] = nums2[j++];
            }else if(i < n1 && j < n2 && nums1[i] <= nums2[j]){
                temp[k] = nums1[i++];
            }else if(i < n1){
                temp[k] = nums1[i++];
            }else{
                temp[k] = nums2[j++];
            }
            k++;
        }

        return sum % 2 == 0 ? (double)(temp[k-1] + temp[k-2]) / 2 : temp[k-1];
    }
    
    
}
