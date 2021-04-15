package com.springbootstudy.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *  第一种监听方式
 *  注：需要在启动类加上该注解@ServletComponentScan //在springboot启动时加载@WebServlet、WebFilter这个注解，并将其实例化
 * @author zxl
 * @date 2020/10/10 10:00
 */
@WebListener
public class FirstListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("FirstListener初始化监听器");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("FirstListener销毁监听器");
    }

}
