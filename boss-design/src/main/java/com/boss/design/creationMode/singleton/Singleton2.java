package com.boss.design.creationMode.singleton;

/**
 * Created by yd on 2019/3/29.
 * 懒汉式   线程不安全
 */
public class Singleton2 {
    // 将自身实例化对象设置为一个属性，并用static修饰
    private static Singleton2 singleton;

    // 构造方法私有化
    private Singleton2() {
    }

    // 静态方法返回该实例
    public static Singleton2 getInstance() {
        if (singleton == null) {
            singleton = new Singleton2();
        }
        return singleton;
    }
}
