package com.javautil.comparable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * 实现Comparator接口的类可以方便的排序， 覆写compareTo接口
 * @author zxl
 * @date 2020/10/27 18:00
 */
@Setter
@Getter
@NoArgsConstructor
public class Student implements Comparable<Student>{

    /*** 名字 */
    private String name;

    /*** 年龄 */
    private int age;

    /*** 分数 */
    private float score;

    /**
     * 排序规则：以分数降序排序，若分数一致，则根据年龄升序排序
     * @param o 比较参数
     * @return 比较结果 -1大于   0等于   1小于
     */
    @Override
    public int compareTo(Student o) {
        /*
         * this.age - o.age 升序
         * o.score - this.score 降序
         */
        return Objects.equals(o.score,this.score) ? this.age - o.age : (int) (o.score - this.score);
    }

    public Student(String name, int age, float score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }

}
