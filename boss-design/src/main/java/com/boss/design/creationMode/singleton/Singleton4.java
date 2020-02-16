package com.boss.design.creationMode.singleton;

/**
 * Created by yd on 2019/3/29.
 * 懒汉式   线程安全
 */
public class Singleton4 {
    // 将自身实例化对象设置为一个属性，并用static修饰
    private static Singleton4 singleton;

    // 构造方法私有化
    private Singleton4() {
    }

    // 静态方法返回该实例,加synchronized关键字实现同步
    public static Singleton4 getInstance() {
        // 第一次检查instance是否被实例化出来，如果没有进入if块
        if (singleton == null) {
            synchronized (Singleton4.class) {
                //某个线程取得了类锁，实例化对象前第二次检查instance是否已经被实例化出来，如果没有，才最终实例出对象
                if (singleton == null) {
                    singleton = new Singleton4();
                }
            }
        }
        return singleton;
    }
}
