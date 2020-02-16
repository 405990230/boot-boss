package com.boss.spring.aop;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

//@Component
public class CityPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("cityServiceImpl"))
            bean = Proxy.newProxyInstance
                (CityPostProcessor.class.getClassLoader(),new Class[]{CityService.class},new CityHandler(bean));
        return bean;
    }
}
