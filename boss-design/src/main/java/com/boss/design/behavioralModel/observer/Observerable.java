package com.boss.design.behavioralModel.observer;

/**
 * Created by yd on 2019/3/20.
 */
public interface Observerable {
    //注册为一个观察者
    void addObserver(Observer o);

    //取消观察者
    void removeObserver(Observer o);

    //通知所有观察者更新信息
    void notifyObserver();
}
