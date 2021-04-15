package com.springbootstudy.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 整合Servlet方式一
 *  注：需要在启动类加上该注解@ServletComponentScan  //在springboot启动时加载@WebServlet、WebFilter这个注解，并将其实例化
 * @author zxl
 * @date 2020/10/10 9:28
 */
@WebServlet(name = "FirstServlet", urlPatterns = "/first")
public class FirstServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        System.out.println("FirstServlet ....");
    }

}
