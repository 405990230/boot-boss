package com.boss.design.creationMode.factory;

/**
 * Created by yd on 2019/3/25.
 * 简单工厂放射实现
 */
public class SimplePhoneFactory1 {
    public static Phone createPhone(Class c) {
        Phone phone = null;
        try {
            phone = (Phone) Class.forName(c.getName()).newInstance();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            System.out.println("不支持抽象类或接口");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("没有足够权限，即不能访问私有对象");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("类不存在");
            e.printStackTrace();
        }
        return phone;
    }
}
