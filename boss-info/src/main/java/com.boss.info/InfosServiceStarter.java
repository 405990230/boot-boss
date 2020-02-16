package com.boss.info;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by qxr4383 on 2018/12/24.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages = {"com.boss", "com.myself.platform"})
@EnableCaching
@ServletComponentScan(basePackages = {"com.boss"})
@EnableTransactionManagement
@MapperScan("com.boss.info.mybatis.dao")
public class InfosServiceStarter {
    public static void main(String[] args) {
        SpringApplication.run(InfosServiceStarter.class, args);
        Runtime runtime = Runtime.getRuntime();
        System.out.println("MAX_MEMBER:" + runtime.maxMemory() / 1024 / 1024);//最大可用内存
        System.out.println("TOTAL_MEMBER:" + runtime.totalMemory() / 1024 / 1024);//默认的可用内存
        System.out.println("FREE_MEMBER:" + runtime.freeMemory() / 1024 / 1024);//可用的内存中空闲的部分

    }
}
