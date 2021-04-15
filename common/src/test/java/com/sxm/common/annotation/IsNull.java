package com.sxm.common.annotation;

import java.lang.annotation.*;

/**
 * 是否为null
 * @author zxl
 * @date 2021/1/11 10:25
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IsNull {

    /**
     * 是否为null
     * @return 默认需要
     */
    boolean isNull() default true;

}
