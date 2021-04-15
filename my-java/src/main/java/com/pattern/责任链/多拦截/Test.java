package com.pattern.责任链.多拦截;


import com.pattern.proxy.HelloWorld;
import com.pattern.proxy.HelloWorldImpl;
import com.pattern.责任链.InterceptorJdkProxy;

public class Test {
    public static void main(String[] args) {

        // proxy1代理真实对象，proxy2代理proxy1，proxy3代理proxy2
        HelloWorld proxy1 = (HelloWorld) InterceptorJdkProxy.bind(new HelloWorldImpl(),"com.study.pattern.责任链.多拦截.Interceptor1");
        HelloWorld proxy2 = (HelloWorld) InterceptorJdkProxy.bind(proxy1,"com.study.pattern.责任链.多拦截.Interceptor2");
        HelloWorld proxy3 = (HelloWorld) InterceptorJdkProxy.bind(proxy2,"com.study.pattern.责任链.多拦截.Interceptor3");
        proxy3.sayHelloWorld();
    }
}
