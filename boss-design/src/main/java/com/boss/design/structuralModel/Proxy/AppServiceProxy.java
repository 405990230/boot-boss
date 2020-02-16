package com.boss.design.structuralModel.Proxy;

/**
 * 代理类--代理实现类
 */
public class AppServiceProxy implements AppService {
    //目标对象
    private AppService appService;
    //通过构造方法传入目标对象
    public AppServiceProxy(AppService appService){
        this.appService = appService;
    }


    @Override
    public String createApp(String name) {
        //在代理对象前我们可以添加一些自己的操作
        System.out.println("before operation");
        String result = appService.createApp(name);
        //在代理对象后我们也可以添加一些自己的操作
        System.out.println("after operation");
        return name;
    }

    @Override
    public void deleteApp(String name) {
        appService.deleteApp(name);
    }
}
