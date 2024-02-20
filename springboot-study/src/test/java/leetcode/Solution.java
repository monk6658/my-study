package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        map.put(target-nums[0],0);
        for (int i = 1; i < nums.length; i++) {
            if(map.containsKey(nums[i])){
                return new int[]{map.get(nums[i]),i};
            }
            map.put(target-nums[i],i);
        }
        return new int[2];
    }

    public boolean isPalindrome(int x) {

//        char[] chars = String.valueOf(x).toCharArray();
//        if(chars.length == 1){
//            return true;
//        }
//        for (int i = 0; i < chars.length/2; i++) {
//            if(chars[i] != chars[chars.length-1-i]){
//                return false;
//            }
//        }
//        return true;

        if(x<0){
            return false;
        }
        int cur = 0;
        int num = x;
        while (num != 0){
            cur = cur*10 + num%10;
            num /= 10;
        }
        return cur == x;
    }

    public int romanToInt(String s) {
        int num = 0;
        for (int i = s.length() - 1; i >=0 ; i--) {
            char c = s.charAt(i);

            int curNum = getIntByRome(c);
            if(i >= 1){
                int preNum = getIntByRome(s.charAt(i - 1));
                if(curNum > preNum){
                    num += (curNum - preNum);
                    i--;
                }else{
                    num += curNum;
                }
            }else{
                num += curNum;
            }
        }

        return num;
    }

    private int getIntByRome(char c){
        switch (c){
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default:return 0;
        }
    }

    public String longestCommonPrefix(String[] strs) {
//        int max = 1;
//        int flag = 0;
//
//        int min = strs[0].length();
//        for (int i = 1; i < strs.length; i++) {
//            if(strs[i].length() < min){
//                min = strs[i].length();
//            }
//        }
//
//        while(max <= min){
//            String s = strs[0].substring(max - 1, max);
//            for (int i = 1; i < strs.length; i++) {
//                if(!Objects.equals(strs[i].substring(max-1,max),s)){
//                    flag = 1;
//                }
//            }
//            if(flag == 1){
//                break;
//            }
//            max++;
//        }
//
//        return strs[0].substring(0, max-1);

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int index = 0;
            int min = Math.min(prefix.length(),strs[i].length());
            while (index < min && prefix.charAt(index) == strs[i].charAt(index)){
                index++;
            }
            if(index == 0){
                prefix = "";
                break;
            }
            prefix = prefix.substring(0,index);
        }

        return prefix;

    }

//    ()[]{}
    public boolean isValid(String s) {
        Map<Character,Character> characterMap = new HashMap<>();
        characterMap.put('(',')');
        characterMap.put('[',']');
        characterMap.put('{','}');

        Stack<Character> stock = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if(stock.isEmpty() && !characterMap.containsKey(c)){
                return false;
            }
            else if(characterMap.containsKey(c)){
                stock.push(c);
            }
            else{
                Character pop = stock.pop();
                if(c != characterMap.get(pop)){
                    return false;
                }
            }
        }

        return stock.empty();
    }


    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }

        if(list1.val < list2.val){
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }
        list2.next = mergeTwoLists(list1, list2.next);
        return list2;
    }

    public int removeDuplicates(int[] nums) {
//        int last = nums[0];
//        int j = 1;
//        for (int i = 1; i < nums.length; i++) {
//            if(last == nums[i]){
//                continue;
//            }
//            last = nums[j++] = nums[i];
//        }
//        System.out.println(Arrays.toString(nums));
//        return j;

        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            if(nums[j] == nums[i]){
                continue;
            }
            nums[++j] = nums[i];
        }
        System.out.println(Arrays.toString(nums));
        return j+1;
    }

    public int removeDuplicates(int[] nums, int k) {
        int j = 0;
        int k1 = 1;
        System.out.println(Arrays.toString(nums));
        for (int i = 1; i < nums.length; i++) {
            if(nums[j] == nums[i] && k1++ >= k){
                continue;
            }

            if(nums[j] != nums[i]){
                k1 = 1;
            }
            nums[++j] = nums[i];
        }
        System.out.println(Arrays.toString(nums));
        return j+1;
    }

    public int removeElement(int[] nums, int val) {

        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != val){
                nums[j++] = nums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
        return j;
    }

    public int searchInsert(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = (right - left)/2 + left;
            if(nums[mid] == target){
                return mid;
            }
            else if(nums[mid] > target){
                right = mid - 1;
            }
            else if(nums[mid] < target){
                left = mid + 1;
            }
        }

        return left;
    }

    public int lengthOfLastWord(String s) {
//        String[] s1 = s.split(" ");
//        return s1[s1.length-1].length();

        int length = 0;
        for (int i = s.length() - 1; i >= 0 ; i--) {
            if(s.charAt(i) == ' ' && length == 0){
                continue;
            }else if(s.charAt(i) == ' ' && length != 0){
                break;
            }
            length++;
        }

        return length;
    }

    public int[] plusOne(int[] digits) {
        // int 存在超出最大值边界问题

        // 非 99
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if(digits[i] != 0){
                return digits;
            }
        }
        // 99
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }


    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int al = a.length() - 1;
        int bl = b.length() - 1;
        int j = 0; // 进位
        while (al >= 0 || bl >= 0){
            int a1 = 0,b1 = 0;
            if(al >= 0){
                a1 = a.charAt(al--) - '0';
            }
            if(bl >= 0){
                b1 = b.charAt(bl--) - '0';
            }
            if(a1 + b1 + j > 2){ // 大于进制位判断
                j = 1;
                result.insert(0,"1");
            }else if(a1 + b1 + j == 2){ // 等于进制位
                j = 1;
                result.insert(0,"0");
            }else if(a1 + b1 + j == 1){
                j = 0;
                result.insert(0,"1");
            }else{
                j = 0;
                result.insert(0,"0");
            }
        }
        if(j == 1){
            result.insert(0, "1");
        }

        return result.toString();
    }

    public int mySqrt(int x) {

        int left = 0, right = x;
        int ans = -1;

        while(left <= right){
            int mid = left + (right - left)/2;

            if(((long) mid * mid) <= x ){
                ans = mid;
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }

        return ans;

//        if(x == 0){
//            return 0;
//        }
//
//        int exp = (int) Math.exp(0.5 * Math.log(x));
//        return (long)(exp+1)*(exp+1) <= x ? exp + 1 : exp;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.mySqrt(2147395599));



        // 11110
//        System.out.println(solution.addBinary("1010","1011"));

//        System.out.println(solution.romanToInt("LVIII"));

//        String[] s = {"dog","racecar","car"};
//        System.out.println(solution.longestCommonPrefix(s));

//        ([)] false   {[]} true
//        System.out.println(solution.isValid("{[]}"));

//        ListNode a1 = new ListNode(1);
//        ListNode a2 = new ListNode(2);
//        a2.next = new ListNode(4);
//        a1.next = a2;
//
//        ListNode a3 = new ListNode(1);
//        ListNode a4 = new ListNode(2);
//        a4.next = new ListNode(4);
//        a3.next = a4;
//        System.out.println();
//        System.out.println(JSON.toJSONString(solution.mergeTwoLists(a1,a3)));

//        int[] nums = {0,0,0,1,1,1,2,2,3,3,4};
//        System.out.println(solution.removeDuplicates(nums,2));

//        int[] nums = {0,1,2,2,3,0,4,2};
//        System.out.println(solution.removeElement(nums,2));

//        int[] nums = {1,3,5};
//        System.out.println(solution.searchInsert(nums,2));

//        System.out.println(solution.lengthOfLastWord(" fly me to the moon  "));

//        int[] nums = {9,9,9};
//        int[] ints = solution.plusOne(nums);
//        System.out.println(Arrays.toString(ints));;


    }

}
