package com.leetcode.str;

//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 10⁴ 
// s 由英文字母、数字、符号和空格组成 
// 
//
// Related Topics 哈希表 字符串 滑动窗口 👍 8487 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static void main(String[] args) {
        // 常规思路， 记录下字符， 出现重复字符开始重新计算长度。 
        // dvdf
        System.out.println(lengthOfLongestSubstring("dvdf"));
        
    }
    public static int lengthOfLongestSubstring(String s) {

        // 滑动窗口[记录位置， 依次向下指向]
        int p1 = 0,p2 = 0,length = s.length();
        int n = 0;
        Map<Character,Integer> map = new HashMap<>();
        while (p2 < length){
            char c = s.charAt(p2);
            if(map.containsKey(c)){
                p1 = Math.max(p1,map.get(c));
            }
            n = Math.max(n,p2 - p1 + 1);
            map.put(c,++p2);
        }

        return n;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

//if(s == null){
//        return 0;
//        }
//        int n = 0;
//        List<Character> set = new ArrayList<>();
//        int i = 0;
//        while (i < s.length()){
//        int temp = 0;
//        while (i < s.length() && !set.contains(s.charAt(i))){
//        set.add(s.charAt(i));
//        temp++;
//        i++;
//        }
//        if(i != s.length()){
//        s = s.substring(set.indexOf(s.charAt(i)) + 1);
//        i = 0;
//        }
//        set.clear();
//        n = Math.max(n,temp);
//        }
//
//        return n;
