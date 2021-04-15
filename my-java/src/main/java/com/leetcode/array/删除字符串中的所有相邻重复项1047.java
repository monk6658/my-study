package com.leetcode.array;

import java.util.Stack;

/**
 * 
 * @author zxl
 * @date 2021/3/9 11:19
 */
public class 删除字符串中的所有相邻重复项1047 {

    /*

    输入："abbaca"
    输出："ca"
    解释：
        例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，
        其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。


     */

    /**
     * 通过出栈、入栈方式删除
     */
    public static String removeDuplicates(String s) {
        if(s == null || s == ""){
            return "";
        }
        int top = -1;
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(top >= 0 && sb.charAt(top) == c){
                sb.deleteCharAt(top);
                top--;
            }else{
                sb.append(c);
                top++;
            }

        }
        return sb.toString();
    }

    /**
     * 使用栈方法，内存小，耗时长
     */
    public static String removeDuplicates1(String s) {
        if(s == null || s == ""){
            return "";
        }
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(!stack.empty() && stack.peek() == c){
                stack.pop();
            }else{
                stack.push(c);
            }
        }
        StringBuffer sb = new StringBuffer();
        stack.forEach(sb::append);
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(removeDuplicates1("axxyz"));
    }

}
