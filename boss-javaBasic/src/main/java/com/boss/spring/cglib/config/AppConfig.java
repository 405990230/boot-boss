package com.boss.spring.cglib.config;

import com.boss.spring.cglib.dao.OrderDao;
import com.boss.spring.cglib.dao.ProductDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.boss.spring.cglib")
public class AppConfig {
    @Bean
    public OrderDao orderDao(){
        productDao();
        return new OrderDao();
    }

    @Bean
    public ProductDao productDao(){
        return new ProductDao();
    }
}
