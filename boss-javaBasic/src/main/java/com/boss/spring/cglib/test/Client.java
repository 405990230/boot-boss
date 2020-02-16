package com.boss.spring.cglib.test;

import com.boss.spring.cglib.config.AppConfig;
import com.boss.spring.cglib.dao.OrderDao;
import com.boss.spring.cglib.dao.ProductDao;
import com.boss.spring.cglib.dao.ProxyHandler;
import com.boss.spring.cglib.dao.UserDao;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;

public class Client {
//    public static void main(String[] args) {
//        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//        System.out.println(ac.getBean(AppConfig.class));
//        //System.out.println(ac.getBean("orderDao").getClass().getSimpleName());
//        System.out.println(ac.getBean(ProductDao.class));
//        System.out.println(ac.getBean(ProductDao.class));
//    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        //增强父类，cglib是基于继承来的
        enhancer.setSuperclass(UserDao.class);
        //Spring的命名策略
        enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
        enhancer.setCallback(new ProxyHandler());
        UserDao dao = (UserDao)enhancer.create();
        dao.query();
    }
}
