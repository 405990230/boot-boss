package com.boss.spring.aop;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Import(CityPostProcessor.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableCity {
}
