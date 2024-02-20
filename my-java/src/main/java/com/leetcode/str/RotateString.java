package com.leetcode.str;

/**
 * 旋转字符串
 */
public class RotateString {


    public static void main(String[] args) {
        System.out.println(new RotateString().rotateString("abcde","abced"));
    }

    /**
     * 给定两个字符串, A 和 B。
     *
     * A的旋转操作就是将A 最左边的字符移动到最右边。例如, 若A = 'abcde'，在移动一次之后结果就是'bcdea'。
     * 如果在若干次旋转操作之后，A能变成B，那么返回True。
     *
     * 示例 1:
     * 输入: A = 'abcde', B = 'cdeab'
     * 输出: true
     *
     * 示例 2:
     * 输入: A = 'abcde', B = 'abced'
     * 输出: false
     */
    public boolean rotateString(String s, String goal) {

        // 常规按个移动比较方法
//        int i = 0;
//        while (i < s.length()){
//            String temp = s.substring(1);
//            temp += s.substring(0,1);
//            if(temp.equals(goal)){
//                return true;
//            }
//            s = temp;
//            i++;
//        }
//        return false;


        // A + A 等于所有移动可能,并比较移动之后字符串长度是否相等即可

        return s.length() == goal.length() && (s + s).contains(goal);
    }




}
