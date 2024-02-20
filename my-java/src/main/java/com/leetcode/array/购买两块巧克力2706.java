package com.leetcode.array;

import com.common.ListNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zxl
 * @create: 2023-12-29 09:40
 **/
public class 购买两块巧克力2706 {


    public static int buyChoco(int[] prices, int money) {
        
        int m1 = 0,m2 = prices.length - 1;
        
        
        for (int i = 1; i < prices.length-1; i++) {
            
            if(prices[m1] > prices[i] && prices[m1] > prices[m2]){
                m1 = i;
            }

            if(m1 != i && prices[m2] > prices[i]){
                m2 = i;
            }
        }

        int last = money - prices[m1] - prices[m2];
        return last >= 0 ? last : money;
    }

    public static int[] twoSum(int[] nums, int target) {

//        for (int i = 0; i < nums.length; i++) {
//            int last = target - nums[i];
//            for (int j = i+1; j < nums.length; j++) {
//                if(last == nums[j]){
//                    return new int[]{i,j};
//                }
//            }
//        }
        
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int last = target - nums[i];
            if(map.containsKey(last)){
                return new int[]{i,map.get(last)};
            }
            map.put(nums[i],i);
        }
        
        return null;        
    }

    /*
    21
    1
     */
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        ListNode re = new ListNode();
        int j = 0;
        
        while (l1 != null && l2 != null){
            
            
            
            re.val = (l1.val + l2.val + j)%10;
            j = (l1.val + l2.val)/10;
            
            if(l1.next != null){
                l1 = l1.next;
            }
            if(l2.next != null){
                l2 = l2.next;
            }
            
        }
        return  null;
    }
    
    public void s(ListNode l1, ListNode l2){
        
        
    }
    
    
    
    public static void main(String[] args) {
        
//        int[] prices = {2,3,1,1};
//        int money = 6;

        int[]  prices = {2,12,93,52,91,86,81,1,79,64};
        int money = 82;
//        System.out.println(buyChoco(prices,money));
        
        System.out.println(Arrays.toString(twoSum(prices,money)));
        
    }
    
    
}
