package com.boss.design.creationMode.singleton;

/**
 * Created by yd on 2019/3/29.
 * 懒汉式   线程安全
 */
public class Singleton3 {
    // 将自身实例化对象设置为一个属性，并用static修饰
    private static Singleton3 singleton;

    // 构造方法私有化
    private Singleton3() {
    }

    // 静态方法返回该实例,加synchronized关键字实现同步
    public static synchronized Singleton3 getInstance() {
        if (singleton == null) {
            singleton = new Singleton3();
        }
        return singleton;
    }
}
