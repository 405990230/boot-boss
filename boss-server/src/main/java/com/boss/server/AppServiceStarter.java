package com.boss.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * TaskApplication [spring boot] 主方法
 * <p>
 * 启动方式，右键->run/debug->Spring Boot App
 *
 * @author
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.boss"})
@EnableCaching
@ServletComponentScan(basePackages = {"com.boss"})
@EnableTransactionManagement
@MapperScan("com.boss.server.dao")
public class AppServiceStarter extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppServiceStarter.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppServiceStarter.class, args);
    }

}
