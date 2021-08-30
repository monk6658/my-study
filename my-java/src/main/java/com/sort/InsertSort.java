package com.sort;

import com.sxm.common.util.PrintUtil;

import java.util.Scanner;

/**
 * 插入排序
 * 时间复杂度 O(n^2)
 * 空间复杂度 O(1)
 */
public class InsertSort {


    public static void main(String[] args) {
        System.out.println("输入规范（数组长度 具体值）输入q退出");
        Scanner scanner = new Scanner(System.in);
        int numLength = scanner.nextInt();
        int[] nums = new int[numLength];
        int i = 0;
        while (scanner.hasNext()){
            String next = scanner.next();
            if(next.equals("q")){
                break;
            }
            nums[i++] = Integer.valueOf(next);
        }
        System.out.print("原数组: ");
        PrintUtil.printArrays(nums);
        int[] ints = insertSort(nums);
        System.out.print("排序后数组: ");
        PrintUtil.printArrays(ints);

    }

    private static int[] insertSort(int[] nums){
        int i,j;
        for(i = 1; i < nums.length; i++ ){
            int temp = nums[i];
            for(j = i - 1; j >= 0; j-- ){
                if(temp <= nums[j]){
                    nums[j+1] = nums[j];
                }else{
                    break;
                }
            }
            nums[j+1] = temp;
        }
        return nums;
    }







}
