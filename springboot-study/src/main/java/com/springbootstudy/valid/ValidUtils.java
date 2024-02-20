package com.springbootstudy.valid;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 注解手动校验 
 */
public class ValidUtils {
    
    /*** 快速失败 */
    private final static Validator VALIDATOR_FAST = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();
    /*** 校验所有 */
    private final static Validator VALIDATOR_ALL = Validation.byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory().getValidator();
    
    /**
     * 校验所有字段并返回不合法字段
     * @param t         参数
     * @param <T>       参数类型
     * @return
     */
    public static <T> StringBuffer validAll(T t){
        Set<ConstraintViolation<T>> errors = VALIDATOR_ALL.validate(t);
        StringBuffer sb = new StringBuffer();
        errors.forEach(v-> sb.append(v.getMessage()).append(";"));
        return sb;
    }

    /**
     * 校验遇到第一个不合法的字段直接返回不合法字段，后续字段不再校验
     * @param t         参数
     * @param <T>       参数类型
     * @return
     */
    public static <T> List<String> validFast(T t){
        Set<ConstraintViolation<T>> errors = VALIDATOR_FAST.validate(t);
        if(errors.size()>0) {
            System.out.println(errors.iterator().next().getPropertyPath() +"："+ errors.iterator().next().getMessage());
        }
        return errors.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
    }
    
    
}
