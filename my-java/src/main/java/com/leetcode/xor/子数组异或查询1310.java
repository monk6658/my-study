package com.leetcode.xor;
/**
 * 
 * @author zxl
 * @date 2021/5/12 10:36
 */
public class 子数组异或查询1310 {

    /*
    有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。
    对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。
    并返回一个包含给定查询 queries 所有结果的数组。
 
    示例 1：
    输入：arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
    输出：[2,7,14,8]
    解释：
    数组中元素的二进制表示形式是：
    1 = 0001
    3 = 0011
    4 = 0100
    8 = 1000
    查询的 XOR 值为：
    [0,1] = 1 xor 3 = 2
    [1,2] = 3 xor 4 = 7
    [0,3] = 1 xor 3 xor 4 xor 8 = 14
    [3,3] = 8
     */
    public static void main(String[] args) {

    }

    /**
     * 前缀和计算（结合律）
     */
    public static int[] xorQueries(int[] arr, int[][] queries) {
        int[] res = new int[queries.length];
        int[] temp = new int[arr.length + 1];
        for(int i = 0; i < arr.length; i++){
            temp[i + 1] = temp[i] ^ arr[i];
        }

        for(int i = 0; i < queries.length; i++){
            res[i] = temp[queries[i][0]] ^ temp[queries[i][1] + 1];
        }
        return res;
    }

    /**
     * 暴力解析
     */
    public static int[] xorQueries1(int[] arr, int[][] queries) {
        int[] res = new int[queries.length];

        for(int i = 0; i < queries.length; i++){

            int i1 = queries[i][0];
            int i2 = queries[i][1];
            if(i1 == i2){
                res[i] = arr[i1];
                continue;
            }
            while (i1 <= i2){
                res[i] ^= arr[i1++];
            }
        }

        return res;
    }
}
