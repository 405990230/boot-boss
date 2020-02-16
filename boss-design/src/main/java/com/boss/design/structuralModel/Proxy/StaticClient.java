package com.boss.design.structuralModel.Proxy;


public class StaticClient {
    public static void main(String[] args) {
        AppService target = new AppServiceImpl();
        AppServiceProxy proxy = new AppServiceProxy(target);
        proxy.createApp("static proxy");
    }
}
