package com.boss.design.structuralModel.decorator;

/**
 * Created by yd on 2019/4/2.
 * 抽象装饰对象角色(Component)：接口，被装饰对象的基类，提供被装饰得方法，被装饰对象和装饰器都实现该接口
 */
public interface Food {
    String getDesc();

    String getCost();
}
