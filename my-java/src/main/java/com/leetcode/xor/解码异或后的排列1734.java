package com.leetcode.xor;

import java.util.Arrays;

/**
 * 
 * @author zxl
 * @date 2021/5/12 9:30
 */
public class 解码异或后的排列1734 {
    /*
    给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。
    它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。比方说，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。
    给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。

    示例 1：
    输入：encoded = [3,1]
    输出：[1,2,3]
    解释：如果 perm = [1,2,3] ，那么 encoded = [1 XOR 2,2 XOR 3] = [3,1]

    示例 2：
    输入：encoded = [6,5,4,6]
    输出：[2,4,1,5,3]
     */
    public static void main(String[] args) {
        int[] encoded = {6,5,4,6};
        int[] decode = decode(encoded);
        System.out.println(Arrays.toString(decode));
    }

    /**
     * 关键点：
     * 1. 前 n 个正整数的排列，且 n 是个 奇数 
     * 2. sum(!decode[0]) = encoded[奇数] ^ encoded[奇数];
     * 3. sum(decode[0..n]) = 1 ^ ... ^ n;
     * 4. decode[0] = sum(!decode[0]) ^ sum(decode[0..n]);  ====> decode[1] = decode[0] ^ encode[0];(异或运算交换律)
     * @param encoded 加密数组
     * @return 解密数组
     */
    private static int[] decode(int[] encoded) {
        int encodedLength = encoded.length;

        int total = 0;
        for(int i = 1; i <= encodedLength + 1; i++){
            total ^= i;
        }

        int odd = 0;
        for(int i = 1; i < encodedLength; i+=2){
            odd ^= encoded[i];
        }

        int[] perm = new int[encodedLength +1];
        perm[0] = total ^ odd;

        for(int i = 0; i < encodedLength; i++){
            perm[i+1] = encoded[i] ^ perm[i];
        }

        return perm;
    }

}
