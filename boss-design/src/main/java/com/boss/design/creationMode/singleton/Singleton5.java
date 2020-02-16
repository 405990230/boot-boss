package com.boss.design.creationMode.singleton;

/**
 * Created by yd on 2019/3/29.
 */
public class Singleton5 {
    private Singleton5 singleton;

    private Singleton5() {
    }

    public static Singleton5 getInstance() {
        return SingletonHolder.sInstance;
    }

    private static class SingletonHolder {
        private static final Singleton5 sInstance = new Singleton5();
    }
}
