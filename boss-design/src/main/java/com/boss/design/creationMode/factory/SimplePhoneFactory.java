package com.boss.design.creationMode.factory;


/**
 * Created by yd on 2019/3/25.
 * 简单工厂
 */
public class SimplePhoneFactory {
    public static Phone createPhone(String type) {
        if (type.equals("xiaomi")) {
            Phone xiaomi = new XiaoMi();
            return xiaomi;
        } else if (type.equals("huawei")) {
            Phone huawei = new HuaWei();
            return huawei;
        } else if (type.equals("iphone")) {
            Phone iphone = new IPhone();
            return iphone;
        } else {
            System.out.println("没有该品牌手机");
            return null;
        }
    }
}
