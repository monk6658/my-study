package com.hwnk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 多组字母排序
 * @author zxl
 * @date 2021/4/13 15:52
 */
public class SortMultiWord {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {

            int count = Integer.parseInt(str);
            String[] strings = new String[count];

            for(int i = 0; i < count; i++){
                strings[i] = br.readLine();
            }

            Arrays.sort(strings);
            StringBuilder sb = new StringBuilder();
            for (String s:strings) {
                sb.append(s).append("\n");
            }
            System.out.println(sb.toString());
        }
    }

}
