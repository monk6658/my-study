package com.hwnk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * 多组去重排序
 * @author zxl
 * @date 2021/4/12 14:08
 */
public class MultiDistinctSort {

    private static void printSet(Set<Integer> set){
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
            iterator.remove();
        }
    }

    private static void my(){
        Scanner scanner = new Scanner(System.in);
        Set<Integer> set = new TreeSet<>();
        int multiLength = scanner.nextInt(),i = 0;
        while (scanner.hasNext()){
            if(i < multiLength){
                set.add(scanner.nextInt());
                i++;
            }else{
                multiLength = scanner.nextInt();
                i = 0;
            }
            if(i == multiLength){
                printSet(set);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;

        while ((str = br.readLine()) != null){
            StringBuilder sb = new StringBuilder();
            boolean[] nums = new boolean[1001];
            Integer length = Integer.valueOf(str);
            for(int i = 0; i <= length; i ++){
                nums[Integer.valueOf(br.readLine())] = true;
            }
            for(int i = 0; i < 1001; i ++){
                if(nums[i]){
                    sb.append(i).append("\n");
                }
            }
            sb.deleteCharAt(sb.length()-1);
            System.out.println(sb.toString());

        }

    }

}
