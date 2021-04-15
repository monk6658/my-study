package com.javautil.comparable;
/**
 * 测试方法
 * @author zxl
 * @date 2020/10/27 18:06
 */
public class Comparable01 {

    public static void main(String args[]){
        Student stu[] = {
                new Student("张三",20,90.0f),
                new Student("李四",22,90.0f),
                new Student("王五",20,99.0f),
                new Student("赵六",20,70.0f),
                new Student("孙七",22,100.0f)} ;
        java.util.Arrays.sort(stu) ;    // 进行排序操作
        for(int i=0;i<stu.length;i++){    // 循环输出数组中的内容
            System.out.println(stu[i]) ;
        }
    }

}
