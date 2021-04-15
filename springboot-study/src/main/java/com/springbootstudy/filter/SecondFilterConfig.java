package com.springbootstudy.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * filter 映射配置方式
 * @author zxl
 * @date 2020/10/10 9:42
 */
@Configuration
public class SecondFilterConfig {

    @Bean
    public FilterRegistrationBean getFilterRegistrationBean(){
        FilterRegistrationBean bean = new FilterRegistrationBean(new SecondFilter());
//        bean.addUrlPatterns(new String[]{"*.jsp","*.do"});
        bean.addUrlPatterns("/second");
        return bean;
    }

}
