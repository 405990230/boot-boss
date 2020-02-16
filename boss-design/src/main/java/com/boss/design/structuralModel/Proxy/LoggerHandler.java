package com.boss.design.structuralModel.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoggerHandler implements InvocationHandler {
    //这个就是我们要代理的真实对象
    private Object target;

    //构造方法，给我们要代理的真实对象赋初值
    public LoggerHandler(Object target){
        this.target = target;
    }

    //Object proxy:被代理的对象
    //Method method:要调用的方法
    //Object[] args:方法调用时所需要参数
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {
        System.out.println("Entered "+target.getClass().getName()+"-"+method.getName()
                                                    +",with arguments{"+args[0]+"}");

        //在代理真实对象前我们可以添加一些自己的操作
        System.out.println("before invoke");

        //当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        Object result = method.invoke(target, args);
        //调用目标对象的方法 （调用厂家的方法（createApp）及参数（Kevin Test））
        System.out.println("Before return:"+result);

        //在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("after invoke");

        return result;
    }
}
