package com.mythread;

import com.alibaba.fastjson.JSON;
import com.javautil.comparable.Student;

import java.util.concurrent.atomic.AtomicReference;

public class SynchronizedTest {
    
    static AtomicReference<Student> atomicReference = new AtomicReference<>(new Student());
    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(()->{
                Student student = atomicReference.get();
                student.setAge(finalI);
                atomicReference.getAndSet(student);
                System.out.println(Thread.currentThread().getId() + ": " + JSON.toJSONString(student));
            }).start();
        }

        for (int i = 5; i < 20; i++) {
            int finalI = i;
            new Thread(()->{
                Student student = atomicReference.get();
                student.setAge(finalI);
                atomicReference.compareAndSet(student,student);
                System.out.println(Thread.currentThread().getId() + ": " + JSON.toJSONString(student));
            }).start();
        }
        
    }
    
    
    
}
