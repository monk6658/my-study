package com.leetcode.str;

import java.util.HashMap;
import java.util.Map;

//一个字符串的 美丽值 定义为：出现频率最高字符与出现频率最低字符的出现次数之差。 
//
// 
// 比方说，"abaacc" 的美丽值为 3 - 1 = 2 。 
// 
//
// 给你一个字符串 s ，请你返回它所有子字符串的 美丽值 之和。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "aabcb"
//输出：5
//解释：美丽值不为零的字符串包括 ["aab","aabc","aabcb","abcb","bcb"] ，每一个字符串的美丽值都为 1 。 
//
// 示例 2： 
//
// 
//输入：s = "aabcbaa"
//输出：17
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 500 
// s 只包含小写英文字母。 
// 
//
// Related Topics 哈希表 字符串 计数 👍 29 👎 0
public class 所有子字符串美丽值之和 {

    public static void main(String[] args) {
        
        String s = "reljcousntjdmfazjzilmzndcxsjaibxrqhehkcjvyhuupbraaxzqripzzaeomtpbjalwplaxsrwckvpsmpgrywbsrcczzkxvlkikdjforygjnkfjxchmqtkjgclpdezgqmuhjzkiwhmbjdlhfadqujujtnimtpkrdnngghawfrlgihrwqcbhhbxlmafyycsizcvrvaydebihigxbokqctuesywaaijunqeqmjnluilcifjrdhlorbrcotngpftwhbiafpuqiompwuwvfzkocxmfcbxfkanedracrhmlgrnyaeerukxvayuadtboqllqq";

        所有子字符串美丽值之和 as = new 所有子字符串美丽值之和();
        Solution solution1 = as.new Solution();
        System.out.println(solution1.beautySum(s));
        
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int beautySum(String s) {
            
            int p1 = 0, p2 = 0;
            int ans = 0;
            while (p1 < s.length()){
                
                Map<Character,Integer> map = new HashMap<>();

                int max = 0;
                while (p2 < s.length()){
                    char c = s.charAt(p2);
                    
                    if(map.containsKey(c)){
                        map.put(c, map.get(c) + 1);
                    }else{
                        map.put(c, 1);
                    }
                    
                    max = Math.max(max,map.get(c));
                    
                    int min = map.get(c);
                    for (Integer value : map.values()) {
                        min = Math.min(min,value);
                    }
                    
                    if(max - min >= 1){
                        ans += (max - min);
                    }
                    
                    p2++;
                }
                
                p1++;
                p2 = p1;
            }
            
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
    
}   
