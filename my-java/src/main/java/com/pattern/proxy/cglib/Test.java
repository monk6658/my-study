package com.pattern.proxy.cglib;


import com.pattern.proxy.ReflectServiceImpl;

public class Test {
    public static void main(String[] args) {

        CglibProxyExample cglibProxyExample = new CglibProxyExample();

        ReflectServiceImpl reflectService = (ReflectServiceImpl) cglibProxyExample.getProxy(ReflectServiceImpl.class);

        reflectService.sayHelloWorld();
    }
}
