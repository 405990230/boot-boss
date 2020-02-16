package com.boss.design.structuralModel.decorator;

/**
 * Created by yd on 2019/4/2.
 * 具体装饰对象角色(ConcreteComponent)：具体被装饰对象，它继承自Component，并实现需要被装饰的方法基本内容；
 */
public class BarbecueFood implements Food {

    @Override
    public String getDesc() {
        return "烧烤";
    }

    @Override
    public String getCost() {
        Double cost = 3.0;
        return String.format("最后价格：%s块", cost);
    }
}
