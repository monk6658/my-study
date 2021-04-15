package com.springbootstudy.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 整合filter方式二
 * @author zxl
 * @date 2020/10/10 9:49
 */

public class SecondFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("SecondFilter执行开始");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("SecondFilter执行结束");
    }

    @Override
    public void destroy() {

    }
}
