package com.boss.design.creationMode.factory;

/**
 * Created by yd on 2019/3/25.
 * 小米手机加工厂
 */
public class XiaoMiFactory implements PhoneFactory {
    public Phone createPhone() {
        return new XiaoMi();
    }
}
