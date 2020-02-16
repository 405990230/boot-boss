package com.boss.design.creationMode.factory;

/**
 * Created by yd on 2019/3/25.
 * 苹果手机工厂
 */
public class IPhoneFactory implements PhoneFactory {
    public Phone createPhone() {
        return new IPhone();
    }
}
