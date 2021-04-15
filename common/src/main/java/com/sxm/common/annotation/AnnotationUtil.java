package com.sxm.common.annotation;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * 注解获取工具
 * @author zxl
 * @date 2021/1/11 9:38
 */
public class AnnotationUtil {

    /**
     * 获取类变量注解
     * @param object 对象
     * @param annotationClass 自定义注解
     */
    public static List<ReflectProperties> findAnnotationByField(Object object, Class<? extends Annotation> annotationClass) {
        List<ReflectProperties> list = new ArrayList<>();
        try {
            Class<?> target = object.getClass();
            // 获取类变量注解：
            Field[] fields = target.getDeclaredFields();
            for (Field f : fields) {
                if(f.isAnnotationPresent(annotationClass)){
                    boolean accessFlag = f.isAccessible();
                    f.setAccessible(true);
                    System.out.println(f.getName() + "," + f.get(object));
                    list.add(new ReflectProperties(f.getName(),f.get(object)));
                    f.setAccessible(accessFlag);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }


    /**
     * 获取方法注解
     * @param target 需获取注解的类
     * @param annotationClass 自定义注解
     * @param <T> 泛型
     */
    public static <T> void findAnnotationByMethod(Class<T> target,Class<? extends Annotation> annotationClass){
        // 获取类方法注解：
        Method[] methods = target.getDeclaredMethods();
        for (Method m : methods) {
            if(m.isAnnotationPresent(annotationClass)){
                System.out.println(m.getAnnotation(annotationClass));
            }
        }
    }

    /**
     * 获取方法参数注解
     * @param target 需获取注解的类
     * @param annotationClass 自定义注解
     * @param <T> 泛型
     */
    public static <T> void findAnnotationByParam(Class<T> target,Class<? extends Annotation> annotationClass){
        // 获取类方法参数注解：
        Method[] methods2 = target.getDeclaredMethods();
        for (Method m : methods2) {
            // 获取方法的所有参数
            Parameter[] parameters = m.getParameters();
            for (Parameter p : parameters) {
                // 判断是否存在注解
                if(p.isAnnotationPresent(annotationClass)){
                    System.out.println(p.getAnnotation(annotationClass));
                }
            }
        }
    }


}
