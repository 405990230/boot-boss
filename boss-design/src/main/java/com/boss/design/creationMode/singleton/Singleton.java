package com.boss.design.creationMode.singleton;

/**
 * Created by yd on 2019/3/29.
 * 饿汉式
 */
public class Singleton {
    // 将自身实例化对象设置为一个属性，并用static、final修饰
    private static final Singleton singleton = new Singleton();

    // 构造方法私有化
    private Singleton() {
    }

    // 静态方法返回该实例
    public static Singleton getInstance() {
        return singleton;
    }
}
