package com.boss.design.structuralModel.adapter;

/**
 * Created by qxr4383 on 2019/4/16.
 */
public class Ashili extends Adapter3 {
    public void a() {
        System.out.println("实现A方法");
    }

    ;

    public void c() {
        System.out.println("实现C方法");
    }

    ;

    public static void main(String[] args) {
        A a = new Ashili();
        a.a();
        a.c();
    }
}
