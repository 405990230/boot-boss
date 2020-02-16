package com.boss.design.structuralModel.decorator;

/**
 * Created by qxr4383 on 2019/4/2.
 * 抽象装饰对象角色(Component)：接口，被装饰对象的基类，提供被装饰得方法，被装饰对象和装饰器都实现该接口
 */
public class HotpotFood implements Food {
    @Override
    public String getDesc() {
        return "火锅";
    }

    @Override
    public String getCost() {
        Double cost = 100.0;
        return String.format("最后价格：%s块", cost);
    }
}
