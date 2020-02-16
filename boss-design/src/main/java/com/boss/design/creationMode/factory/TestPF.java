package com.boss.design.creationMode.factory;

/**
 * Created by yd on 2019/3/25.
 */
public class TestPF {
    public static void main(String[] args) {
        HuaWeiFactory huaWeiFactory = new HuaWeiFactory();
        Phone huawei = huaWeiFactory.createPhone();
        System.out.print("工厂：");
        huawei.getPhone();

        XiaoMiFactory xiaoMiFactory = new XiaoMiFactory();
        Phone xiaomi = xiaoMiFactory.createPhone();
        System.out.print("工厂：");
        xiaomi.getPhone();
    }
}
