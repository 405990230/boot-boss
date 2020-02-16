package com.boss.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * TaskApplication [spring boot] 主方法
 * <p>
 * 启动方式，右键->run/debug->Spring Boot App
 *
 * @author
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages = {"com.boss"})
@EnableCaching
@EnableScheduling
@ServletComponentScan(basePackages = {"com.boss.cache"})
public class BackendCacheStarter {

    public static void main(String[] args) {
        SpringApplication.run(BackendCacheStarter.class, args);
    }

}
