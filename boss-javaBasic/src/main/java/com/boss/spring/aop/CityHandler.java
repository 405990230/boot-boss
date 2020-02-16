package com.boss.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CityHandler implements InvocationHandler {
    private Object object;
    public CityHandler(Object object){
        this.object = object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("CityHandler -------------------");
        return method.invoke(object,args);
    }
}
