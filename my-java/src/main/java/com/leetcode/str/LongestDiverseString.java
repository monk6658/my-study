package com.leetcode.str;

/**
 * 最长快乐字符串
 */
public class LongestDiverseString {

    public static void main(String[] args) {
        System.out.println((Integer)null);
//        System.out.println(longestDiverseString(7,1,0));
    }

    /**
     * 如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
     *
     * 给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
     *
     * s 是一个尽可能长的快乐字符串。
     * s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
     * s 中只含有 'a'、'b' 、'c' 三种字母。
     * 如果不存在这样的字符串 s ，请返回一个空字符串 ""。
     *
     * 输入：a = 1, b = 1, c = 7
     * 输出："ccaccbcc"
     * 解释："ccbccacc" 也是一种正确答案。
     *
     * 输入：a = 2, b = 2, c = 1
     * 输出："aabbc"
     *
     * 输入：a = 7, b = 1, c = 0
     * 输出："aabaa"
     * 解释：这是该测试用例的唯一正确答案。
     *
     */
    public static String longestDiverseString(int a, int b, int c) {
        int[] nu = new int[]{a,b,c};
        char[] result = new char[a + b + c];

        int idx = 0;
        while (nu[0] !=0 || nu[1]!=0 || nu[2]!=0){
            if(idx < 2 || result[idx-1] != result[idx-2]){
                // 小于2 取最大两个数
                
            }else{
  
                
            }
            
            
            
            
            
        }
        
        
        
        
        return new String(result);
    }

}
