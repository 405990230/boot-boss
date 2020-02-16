package com.boss.design.creationMode.factory;


/**
 * Created by yd on 2019/3/25.
 * 手机工厂
 */
public interface MobileFactory {
    Phone createPhone();

    Screen createScreen();

    Camera createCamera();
}
