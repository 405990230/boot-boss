package com.spring.aop.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;


@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = false)
@ComponentScan("com.spring.aop")
public class AppConfig {
}
