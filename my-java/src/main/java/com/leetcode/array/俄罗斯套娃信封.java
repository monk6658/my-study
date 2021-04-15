package com.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 二维数组俄罗斯套娃信封问题
 * @author zxl
 * @date 2021/3/8 9:12
 */
public class 俄罗斯套娃信封 {

    /*
    动态规划基于固定第一个值，依次升序第二个值比较
    最长严格递增子序列问题，越长的子序列的末尾元素显然越大。
     */
    /**
     * 动态规划
     * @param envelopes 信封二维数组
     * @return 个数
     */
    public static int maxEnvelopes(int[][] envelopes) {

        int length = envelopes.length;
        if(length == 0){
            return 0;
        }

        Arrays.sort(envelopes, (e1, e2) -> {
            if (e1[0] != e2[0]) {
                return e1[0] - e2[0];
            } else {
                return e2[1] - e1[1];
            }
        });
        for (int i = 0; i < length; i++){
            System.out.println(Arrays.toString(envelopes[i]));
        }

        int[] f = new int[length];
        Arrays.fill(f,1);
        int num = 1;
        for(int i = 1; i < length; i++){

            for(int j = 0; j < i; j++){
                if(envelopes[i][1] > envelopes[j][1]){
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }

            num = Math.max(f[i],num);
        }

        return num;
    }

    /**
     * 基于二分查找的动态规划
     * @param envelopes 二维数组信息
     * @return 个数
     */
    public static int maxEnvelopes1(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }

        int n = envelopes.length;
        Arrays.sort(envelopes, (e1, e2) -> {
            if (e1[0] != e2[0]) {
                return e1[0] - e2[0];
            } else {
                return e2[1] - e1[1];
            }
        });
        for (int i = 0; i < n; i++){
            System.out.println(Arrays.toString(envelopes[i]));
        }

        List<Integer> f = new ArrayList<>();
        f.add(envelopes[0][1]);
        for (int i = 1; i < n; ++i) {
            int num = envelopes[i][1];
            if (num > f.get(f.size() - 1)) {
                f.add(num);
            } else {
                int index = binarySearch(f, num);
                f.set(index, num);
            }
        }
        return f.size();
    }

    public static int binarySearch(List<Integer> f, int target) {
        int low = 0, high = f.size() - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (f.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }



    public static void main(String[] args) {
        List<Integer> f = new ArrayList<>();
        int[][] envelopes1 = {
                {1,6},
                {2,3},
                {3,4},
                {4,5},
                {5,7},
                {6,8}
        };

//        [[5,4],[6,4],[6,7],[2,3]]
        int[][] envelopes2 = {{5,4},{6,4},{6,7},{2,3},{7,5},{8,6}};


        Arrays.sort(envelopes2,(e1,e2) -> e1[0] != e2[0] ? e1[0] - e2[0] : e2[1] - e1[1]);
        for (int i = 0; i < envelopes2.length; i++){
            System.out.println(Arrays.toString(envelopes2[i]));
        }


//        int i = maxEnvelopes(envelopes2);
//        System.out.println(i);
    }



}
