package com.niuke.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * 
 * @author zxl
 * @date 2021/5/19 14:12
 */
public class 最小的K个数 {

    /*
    给定一个数组，找出其中最小的K个数。例如数组元素是4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。如果K>数组的长度，
    那么返回一个空的数组

    示例1
        输入
        [4,5,1,6,2,7,3,8],4
        返回值
        [1,2,3,4]
    */
    public static ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        return collect(input,k);
    }

    /**
     * 数组特性解法
     * @param input 源数组
     * @param k 最小个数
     * @return 最小个数集合
     */
    private static ArrayList<Integer> array(int[] input, int k){
        int length = input.length;
        ArrayList<Integer> integers = new ArrayList<>();
        if(k== 0 || k > length){
            return new ArrayList<>();
        }
        Arrays.sort(input);
        for(int i = 0; i < k; i++){
            integers.add(input[i]);
        }

        return integers;
    }

    /**
     * 集合特性解法
     * @param input 源数组
     * @param k 最小个数
     * @return 最小个数集合
     */
    private static ArrayList<Integer> collect(int[] input, int k){
        int length = input.length;
        if(k== 0 || k > length){
            return new ArrayList<>();
        }

        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int j : input) {
            treeSet.add(j);
        }

        return new ArrayList<>(treeSet.subSet(0, k+1));
    }





    public static void main(String[] args) {
        GetLeastNumbers_Solution(new int[]{4, 5, 1, 6, 2, 7, 3, 8},4);
    }

}
