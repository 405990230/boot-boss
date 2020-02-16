package com.boss.design.structuralModel.adapter;

/**
 * Created by yd on 2019/4/16.
 * 类适配器模式
 * 原理：通过继承来实现适配器功能。
 */
public class Adapter2 implements Apple {
    private AndroidUser user;

    public Adapter2(AndroidUser user) {
        this.user = user;
    }

    @Override
    public void apple() {
        user.android();
    }

    public static void main(String[] args) {
        Apple apple2 = new Adapter2(new AndroidUser());
        apple2.apple();
    }
}