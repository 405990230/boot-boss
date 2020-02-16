package com.boss.design.creationMode.factory;

/**
 * Created by yd on 2019/3/25.
 * 华为手机加工厂
 */
public class HuaWeiFactory implements PhoneFactory {
    public Phone createPhone() {
        return new HuaWei();
    }
}
