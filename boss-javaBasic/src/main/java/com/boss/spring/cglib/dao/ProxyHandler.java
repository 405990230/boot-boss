package com.boss.spring.cglib.dao;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyHandler implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //可以在这个方法中添加各种判断
//        if(判断是否被代理过){
//            return methodProxy.invokeSuper(o,objects);
//        } else{
//            getBean()
//        }
        System.out.println("cglib ***************");
        return methodProxy.invokeSuper(o,objects);
    }
}
