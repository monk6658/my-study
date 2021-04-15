package com.sxm.common.annotation;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 注解测试
 * @author zxl
 * @date 2021/4/9 18:27
 */
@Slf4j
public class TestAnnotationUtil {

    public static void main(String[] args) {

        AnnotationVo annotationVo = new AnnotationVo();
        annotationVo.setId("id");

        // 1. 通过反射，获取对象中使用注解的  变量以及值信息
        List<ReflectProperties> annotationByField = AnnotationUtil.findAnnotationByField(annotationVo, IsNull.class);
        if(annotationByField == null || annotationByField.size() == 0){
            log.error("找不到注解");
        }

        // 2. 遍历变量和值
        assert annotationByField != null;
        annotationByField.forEach((k)->log.info("获取存在注解的名称: {}, 值: {}",k.getName(),k.getValue()));

    }

}
