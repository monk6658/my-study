package com.pattern.责任链.拦截器实现;


import com.pattern.proxy.HelloWorld;
import com.pattern.proxy.HelloWorldImpl;
import com.pattern.责任链.InterceptorJdkProxy;

public class Test {
    public static void main(String[] args) {
        HelloWorld helloWorld = (HelloWorld) InterceptorJdkProxy.bind(new HelloWorldImpl(),"com.study.pattern.责任链.拦截器实现.MyInterceptor");
        helloWorld.sayHelloWorld();
    }
}
