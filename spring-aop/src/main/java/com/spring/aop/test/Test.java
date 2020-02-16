package com.spring.aop.test;

import com.spring.aop.app.AppConfig;
import com.spring.aop.dao.Dao;
import com.spring.aop.dao.IndexDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);
        Dao dao = (Dao)ctx.getBean("indexDao");
        dao.query();
        dao.get();

        System.out.println(dao instanceof IndexDao);
    }
}
