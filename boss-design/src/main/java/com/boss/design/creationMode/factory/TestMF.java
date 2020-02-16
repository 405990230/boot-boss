package com.boss.design.creationMode.factory;

/**
 * Created by qxr4383 on 2019/3/25.
 * 测试类
 */
public class TestMF {
    public static void main(String[] args) {
        MobileFactory factory = new HighMobileFactory();
        Screen screen = factory.createScreen();
        screen.getScreen();
        Camera camera = factory.createCamera();
        camera.getCamera();
    }
}
