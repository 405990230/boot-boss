package com.boss.design.creationMode.factory;

/**
 * Created by yd on 2019/3/25.
 * 手机屏
 */
public interface Screen {
    void getScreen();
}

class LowScreen implements Screen {
    public void getScreen() {
        System.out.println("生产低挡手机屏");
    }
}

class HighScreen implements Screen {
    public void getScreen() {
        System.out.println("生产高挡手机屏");
    }
}