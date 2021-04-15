package com.hwnk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * 坐标计算 X(a-d+),y(w+s-)
 * 合法坐标为A(或者D或者W或者S) + 数字（两位以内）坐标之间以;分隔。
 *
 * @author zxl
 * @date 2021/4/13 17:12
 */
public class CoordinateCalc {
    private static Pattern pattern = Pattern.compile("^[A|S|W|D]+[0-9]{1,2}$");

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {
            int x = 0,y = 0;
            String[] split = str.split(";");
            for (String s : split) {
                if(pattern.matcher(s).matches()){
                    if(s.charAt(0) == 'A'){
                        x -= Integer.parseInt(s.substring(1));
                    }else if(s.charAt(0) == 'D'){
                        x += Integer.parseInt(s.substring(1));
                    } else if(s.charAt(0) == 'W'){
                        y += Integer.parseInt(s.substring(1));
                    } else if(s.charAt(0) == 'S'){
                        y -= Integer.parseInt(s.substring(1));
                    }
                }
            }
            System.out.println(x + "," + y);
        }
    }

}
