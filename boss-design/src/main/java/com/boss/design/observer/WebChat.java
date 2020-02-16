package com.boss.design.observer;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者，也就是微信公众号服务
 * 实现了Observerable接口，对Observerable接口的三个方法进行了具体实现
 * Created by yd on 2019/3/20.
 */
public class WebChat implements Observerable {
    private List<Observer> list;
    private String message;

    public WebChat() {
        list = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer o) {
        list.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if (!list.isEmpty()) {
            list.remove(o);
        }
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : list) {
            observer.update(message);
        }
    }

    public void setInfo(String a) {
        this.message = a;
        System.out.println("微信公众号更新，秒杀价格为" + a);
        notifyObserver();
    }
}
