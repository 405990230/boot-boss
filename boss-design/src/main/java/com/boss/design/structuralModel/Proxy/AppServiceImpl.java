package com.boss.design.structuralModel.Proxy;


/**
 * 具体实现类
 */
public class AppServiceImpl implements AppService {
    @Override
    public String createApp(String name) {
        System.out.println("1----App["+name+"] has been created.");
        return name;
    }

    @Override
    public void deleteApp(String name) {
        System.out.println("2------App["+name+"] has been delete.");
    }
}
