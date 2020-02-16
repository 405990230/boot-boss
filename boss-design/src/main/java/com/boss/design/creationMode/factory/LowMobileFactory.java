package com.boss.design.creationMode.factory;

/**
 * Created by yd on 2019/3/25.
 * 抵挡手机配件工厂
 */
public class LowMobileFactory implements MobileFactory {
    @Override
    public Phone createPhone() {
        return new XiaoMi();
    }

    @Override
    public Screen createScreen() {
        return new LowScreen();
    }

    @Override
    public Camera createCamera() {
        return new LowCamera();
    }
}
