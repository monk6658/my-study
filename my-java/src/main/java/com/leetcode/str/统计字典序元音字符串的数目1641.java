package com.leetcode.str;

//给你一个整数 n，请返回长度为 n 、仅由元音 (a, e, i, o, u) 组成且按 字典序排列 的字符串数量。 
//
// 字符串 s 按 字典序排列 需要满足：对于所有有效的 i，s[i] 在字母表中的位置总是与 s[i+1] 相同或在 s[i+1] 之前。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 1
//输出：5
//解释：仅由元音组成的 5 个字典序字符串为 ["a","e","i","o","u"]
// 
//
// 示例 2： 
//
// 
//输入：n = 2
//输出：15
//解释：仅由元音组成的 15 个字典序字符串为
//["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"]
//注意，"ea" 不是符合题意的字符串，因为 'e' 在字母表中的位置比 'a' 靠后
// 
//
// 示例 3： 
//
// 
//输入：n = 33
//输出：66045
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 50 
// 
//
// Related Topics 数学 动态规划 组合数学 👍 97 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
public class 统计字典序元音字符串的数目1641 {

//        1 2  3  4
//
//        1 5 15 35
//        1 4 10 20
//        1 3 6  10
//        1 2 3  4
//        1 1 1  1
    
    public static void main(String[] args) {
        /*
        解题思路：
            1. 当前组合均为 当前后一个字符的 排序值 + 上一次排序值，  
         */
        
        System.out.println(countVowelStrings(33));
    }
    
    public static int countVowelStrings(int n) {
        
        int[][] temp = new int[5][n];
        // 初始化第一列为1
        for (int i = 0; i < 5; i++) {
            temp[i][0] = 1;
        }
        // 最后一列也为1
        for (int j = 1; j < n; j++) {
            temp[4][j] = 1;
        }
        
        for (int i = 3; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                temp[i][j] = temp[i+1][j] + temp[i][j-1];
            }
        }
        
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += temp[i][n-1];
        }
        
        return sum;
    }
    
    
}
