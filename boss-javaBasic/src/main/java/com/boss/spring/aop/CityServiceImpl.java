package com.boss.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class CityServiceImpl implements CityService {

    public void query(){
        System.out.println("spring -----init -----");
    }
}
