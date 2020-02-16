package com.boss.design.creationMode.factory;

/**
 * Created by yd on 2019/3/25.
 * 产品的抽象接口：手机
 */
public interface Phone {
    void getPhone();
}

/**
 * 华为手机
 */
class HuaWei implements Phone {
    @Override
    public void getPhone() {
        System.out.println("我需要生产的手机品牌为：华为");
    }
}

/**
 * 小米手机
 */
class XiaoMi implements Phone {
    @Override
    public void getPhone() {
        System.out.println("我需要生产的手机品牌为：小米");
    }
}

/**
 * 苹果手机
 */
class IPhone implements Phone {
    @Override
    public void getPhone() {
        System.out.println("我需要生产的手机品牌为：苹果");
    }
}

