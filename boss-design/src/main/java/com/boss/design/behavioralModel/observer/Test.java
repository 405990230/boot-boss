package com.boss.design.behavioralModel.observer;

/**
 * Created by yd on 2019/3/20.
 */
public class Test {
    public static void main(String[] args) {
        WebChat w = new WebChat();
        User a = new User("0001");
        User b = new User("0002");
        User c = new User("0003");
        w.addObserver(a);
        w.addObserver(b);
        w.addObserver(c);
        w.setInfo("1999.00");
        w.removeObserver(c);
        w.setInfo("999.00");
    }
}
