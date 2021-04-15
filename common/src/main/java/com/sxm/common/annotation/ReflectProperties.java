package com.sxm.common.annotation;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 反射对象属性
 * @author zxl
 * @date 2021/1/11 10:59
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReflectProperties {

    /*** 反射对象变量名 */
    private String name;

    /*** 反射对象变量值 */
    private Object value;

}
