package com.boss.design.structuralModel.decorator;

/**
 * Created by yd on 2019/4/2.
 * 创建PepperFoodDecorator装饰器类
 */
public class PepperFoodDecorator extends FoodDecorator {
    public PepperFoodDecorator(Food food) {
        super(food);
    }

    @Override
    public String getDesc() {
        String result = "加盐的" + food.getDesc();

        return result;
    }

    @Override
    public String getCost() {
        Double pepper = 10.0;
        System.out.println(String.format("加辣椒多收%s块", pepper));
        String result = food.getCost() + " + " + pepper + "块";
        return result;
    }
}
