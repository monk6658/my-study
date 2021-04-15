package com.springbootstudy.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 整合Servlet方式二
 * @author zxl
 * @date 2020/10/10 9:28
 */
public class SecondServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        System.out.println("SecondServlet ....");
    }

}
