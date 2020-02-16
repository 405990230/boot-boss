package com.boss.info.filter;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;

/**
 * Created by qxr4383 on 2019/3/15.
 */
@Configuration
public class ConfigurationFilter {
    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean<BasePathVariableFilter> testFilterRegistration() {
        FilterRegistrationBean<BasePathVariableFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new BasePathVariableFilter());//添加过滤器
        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
        registration.addInitParameter("admin", "moshow");//添加默认参数
        registration.setName("BasePathVariableFilter");//设置优先级
        registration.setOrder(1);//设置优先级
        return registration;
    }

    @Bean
    public FilterRegistrationBean<HiddenHttpMethodFilter> testFilterRegistration3() {
        FilterRegistrationBean<HiddenHttpMethodFilter> registration = new FilterRegistrationBean<HiddenHttpMethodFilter>();
        registration.setFilter(new HiddenHttpMethodFilter());//添加过滤器
        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
        registration.setName("HiddenHttpMethodFilter");//设置优先级
        registration.setOrder(2);//设置优先级
        return registration;
    }

    @Bean
    public FilterRegistrationBean<HttpPutFormContentFilter> testFilterRegistration2() {
        FilterRegistrationBean<HttpPutFormContentFilter> registration = new FilterRegistrationBean<HttpPutFormContentFilter>();
        registration.setFilter(new HttpPutFormContentFilter());//添加过滤器
        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
        registration.setName("HttpPutFormContentFilter");//设置优先级
        registration.setOrder(2);//设置优先级
        return registration;
    }
}
