package com.springbootstudy.valid;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Set;

public class TestValid {

    public static void main(String[] args) {
        StudentVo studentVo = new StudentVo();
        studentVo.setName("1");
        studentVo.setDate("1995/12/12");
        studentVo.setActivity_cost(new BigDecimal(-1));

        System.out.println(ValidUtils.validAll(studentVo));

        LocalDate now = LocalDate.now();
        System.out.println(now.getMonth().firstMonthOfQuarter().getValue());
        Month month = Month.of(now.getMonth().firstMonthOfQuarter().getValue());
        System.out.println(now.isLeapYear());
        System.out.println(LocalDate.of(now.getYear(), month, 1));

        month = Month.of(now.getMonth().firstMonthOfQuarter().getValue()).plus(2L);
        System.out.println(LocalDate.of(now.getYear(), month, 1).toString().substring(0,7));

        System.out.println(LocalDate.now().with(TemporalAdjusters.firstDayOfYear()));
        
        
        
        
//        
//        System.out.println(LocalDate.parse("2023-07-01"));
//        
//        JSONObject jsonObject = new JSONObject();
//        System.out.println(jsonObject.getString("aaa"));
//        
//        System.out.println(LocalDate.parse("2022-12-20 12:54:12", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//
//        System.out.println(transferString2Date("2022-12-20 12:54:12"));
//        
//        System.out.println(new BigDecimal("1.00000000").subtract(new BigDecimal("1")).setScale(8));
//        
//        studentVo.setSex("男");
////        System.out.println(valid(studentVo));
//
////        System.out.println(JSON.toJSONString(ValidUtils.validFast(studentVo)));
//        
//        System.out.println(new Date("2023/6/14").compareTo(new Date("2023/6/14")));
////        
//        long betweenDay = DateUtil.betweenDay(new Date("2023/6/14"), new Date("2023/6/13"),false);
//        System.out.println(betweenDay);
////        System.out.println(NumberUtil.isNumber("-20.2"));
        
    }

    public static <T> StringBuffer valid(T t){
        Set<ConstraintViolation<T>> errors = Validation.buildDefaultValidatorFactory().getValidator().validate(t);
        StringBuffer sb = new StringBuffer();
        errors.forEach(v-> sb.append(v.getMessage()).append(";"));
        return sb;
    }

    public static LocalDate transferString2Date(String s) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
        } catch (ParseException e) {
            //LOGGER.error("时间转换错误, string = {}", s, e);
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
}
