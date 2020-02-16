package com.boss.design.creationMode.factory;

/**
 * Created by yd on 2019/3/25.
 */
public class TestSPF {
    public static void main(String[] args) {
        Phone xiaomi = SimplePhoneFactory.createPhone("xiaomi");
        xiaomi.getPhone();
        Phone iphone = SimplePhoneFactory.createPhone("iphone");
        iphone.getPhone();
        Phone meizu = SimplePhoneFactory.createPhone("meizu");
        //meizu.getPhone();

        Phone xiaomi1 = SimplePhoneFactory1.createPhone(XiaoMi.class);
        xiaomi1.getPhone();
        Phone iphone1 = SimplePhoneFactory1.createPhone(IPhone.class);
        iphone1.getPhone();
    }
}
