package com.boss.design.creationMode.factory;


/**
 * Created by yd on 2019/3/25.
 * 高配手机配件工厂
 */
public class HighMobileFactory implements MobileFactory {
    @Override
    public Phone createPhone() {
        return new HuaWei();
    }

    @Override
    public Screen createScreen() {
        return new HighScreen();
    }

    @Override
    public Camera createCamera() {
        return new HighCamera();
    }
}
