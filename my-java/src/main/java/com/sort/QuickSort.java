package com.sort;

/**
 * 快速排序
 *
 * @author zxl
 * @date 2021/5/19 16:31
 */
public class QuickSort {


    /**
     * 快速排序
     * @param num 待排序数组
     * @return 排序之后的数组
     */
    public int[] quickSort(int[] num){

        int low = 0;
        int high = num.length - 1;
        int mid = (high - low) >> 1;

        while (low < high){
            quickSort(num,low,high);

            high = mid;
            quickSort(num,low,high);


        }

        return null;
    }


    public void quickSort(int[] num,int low,int high){
        int mid = (high - low) >> 1;
        while (low < high){
            while (low < high && num[low] <= num[mid]){
                low++;
            }
            swap(num[low],num[mid]);

            while (low < high && num[high] > num[mid]){
                high--;
            }
            swap(num[high],num[mid]);
        }

    }


    private void swap(int a,int b){
        int temp = a;
        a = b;
        b = temp;
    }




}
