package com.boss.design.creationMode.factory;

/**
 * Created by yd on 2019/3/25.
 * 定义一个抽象的手机工厂
 */
public interface PhoneFactory {

    /**
     * 生产手机
     *
     * @return
     */
    Phone createPhone();
}
