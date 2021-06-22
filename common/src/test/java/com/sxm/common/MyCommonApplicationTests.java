package com.sxm.common;

import com.sxm.common.util.SpringUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyCommonApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(1);
    }

    @Test
    void dd() throws ClassNotFoundException {
        System.out.println("----- bean ");
        System.out.println(getClass().getName());
        System.out.println(Class.forName("com.sxm.common.config.BeanConfig").getName());
        Object bean = SpringUtil.getBean(Class.forName("com.sxm.common.config.BeanConfig"));
        System.out.println(bean);
    }

}
