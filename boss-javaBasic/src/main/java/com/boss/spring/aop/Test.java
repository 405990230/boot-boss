package com.boss.spring.aop;

import org.springframework.aop.framework.AopProxy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Appconfig.class);
        //Spring容器就是一个ConcurrentHashMap
        //代理对象不是在getBean的事后代理的，代理是在init初始化的时候完成了
        ac.getBean(CityService.class).query();
    }
}
