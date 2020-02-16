package com.boss.spring.cglib.postProcessor;

import com.boss.spring.cglib.dao.ProductDao;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class OrderBeanPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        GenericBeanDefinition appconfig = (GenericBeanDefinition) configurableListableBeanFactory.getBeanDefinition("appConfig");
        String[] attrs = appconfig.attributeNames();
        for(String attr : attrs){
            System.out.println("----------key :"+attr);
            System.out.println("----------value :"+appconfig.getAttribute(attr));
        }
    }
}
