package com.javautil.comparator;

import java.util.Comparator;
import java.util.Objects;

/**
 * 
 * @author zxl
 * @date 2020/10/27 18:14
 */
public class ComparatorTest {

    public static void main(String[] args) {
        Student stu[] = {
                new Student("张三",20,90.0f),
                new Student("李四",22,90.0f),
                new Student("王五",20,99.0f),
                new Student("赵六",20,70.0f),
                new Student("孙七",22,100.0f)} ;

        // 排序方法一
        java.util.Arrays.sort(stu, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                /*
                 * o1.param - o2.param 升序
                 * o2.param - o1.param 降序
                 */
                return Objects.equals(o1.getScore(),o2.getScore()) ? o1.getAge() - o2.getAge() : (int) (o2.getScore() - o1.getScore());
            }
        }) ;

        // 排序方法二 lambda方法
        java.util.Arrays.sort(stu, (o1, o2) -> Objects.equals(o1.getScore(),o2.getScore()) ? o1.getAge() - o2.getAge() : (int) (o2.getScore() - o1.getScore())) ;


        for(int i=0;i<stu.length;i++){    // 循环输出数组中的内容
            System.out.println(stu[i]) ;
        }
    }

    /**
     * 排序方法三：该方法需新建一个java类使用，
     * java.util.Arrays.sort(stu, new MyComparator());
     */
    class MyComparator implements Comparator<Student>{
        @Override
        public int compare(Student o1, Student o2) {
            return Objects.equals(o1.getScore(),o2.getScore()) ? o1.getAge() - o2.getAge() : (int) (o2.getScore() - o1.getScore());
        }
    }

}
