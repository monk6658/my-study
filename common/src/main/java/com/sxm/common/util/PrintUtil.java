package com.sxm.common.util;

import java.util.Arrays;
import java.util.List;

/**
 * 基础工具类
 * @author zxl
 * @date 2020/11/2 11:11
 */
public class PrintUtil {


    /**
     * 遍历集合
     * @param param 参数
     */
    public static void printList(List param){
        for (Object o : param) {
            System.out.print(o + " ");
        }
        System.out.println();
    }

    /**
     * 输出二维数组
     * @param intervals 二维数组值
     */
    public static void printArrays(int[][] intervals){
        for (int[] ints:intervals) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println();
    }

    /**
     * 输出一维数组
     * @param intervals 一维数组值
     */
    public static void printArrays(int[] intervals){
        System.out.println(Arrays.toString(intervals));
    }

}
