package com.springbootstudy.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 整合filter方式一
 *  注：需要在启动类加上该注解@ServletComponentScan //在springboot启动时加载@WebServlet、WebFilter这个注解，并将其实例化
 * @author zxl
 * @date 2020/10/10 9:49
 */
//@WebFilter(filterName = "FirstFilter",urlPatterns = {"*.do","*.jsp"})
@WebFilter(filterName = "FirstFilter",urlPatterns = "/first")
public class FirstFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("FirstFilter执行开始");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("FirstFilter执行结束");
    }

    @Override
    public void destroy() {

    }
}
