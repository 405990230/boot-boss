package com.boss.design.structuralModel.adapter;

/**
 * Created by yd on 2019/4/16.
 * 类适配器模式
 * 原理：通过继承来实现适配器功能。
 */
public class Adapter extends AndroidUser implements Apple {
    @Override
    public void apple() {
        android();
    }

    /**
     * Created by yd on 2019/4/16.
     * 适配器模式：将一个类的方法接口转换成客户希望的另外一个接口。
     * <p>
     * 测试类
     * 实例讲解：
     * 　　我有一个Apple充电器，但是我是Android的手机，怎么办呢？弄个转换器，
     * 将Apple充电器转换成为Android充电器就可以使用了。
     * 　　接口Apple：描述苹果充电器接口格式
     * 　　接口Android：描述安卓充电器接口格式
     * 　　类AndroidUser：是接口Android的实现类，是具体的接口Android接口格式
     * 　　Adapter：用于将Apple接口格式转换成为Android接口格式
     */
    public static void main(String[] args) {
        Apple apple = new Adapter();
        apple.apple();
    }
}