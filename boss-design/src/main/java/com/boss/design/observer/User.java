package com.boss.design.observer;

import lombok.extern.slf4j.Slf4j;

/**
 * 观察者
 * 实现了update方法
 *
 * @author yd
 */
@Slf4j
public class User implements Observer {
    private String name;
    private String message;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        this.message = message;
        read();
    }

    public void read() {
        log.info("恭喜你：" + name + ",你已经获得秒杀资格，价格为" + message + "元");
    }
}
