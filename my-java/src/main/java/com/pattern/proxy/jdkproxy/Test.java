package com.pattern.proxy.jdkproxy;


import com.pattern.proxy.HelloWorld;
import com.pattern.proxy.HelloWorldImpl;

public class Test {

    public static void main(String[] args) {
        JdkProxyExample jdkProxyExample = new JdkProxyExample();
        //绑定关系,
        HelloWorld helloWorld = (HelloWorld) jdkProxyExample.bind(new HelloWorldImpl());
        helloWorld.sayHelloWorld();
    }
}
