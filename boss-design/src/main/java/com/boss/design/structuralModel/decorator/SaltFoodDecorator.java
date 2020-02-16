package com.boss.design.structuralModel.decorator;

/**
 * Created by yd on 2019/4/2.
 * 创建SaltFoodDecorator装饰器类
 */
public class SaltFoodDecorator extends FoodDecorator {

    public SaltFoodDecorator(Food food) {
        super(food);
    }

    @Override
    public String getDesc() {
        String result = "加盐的" + food.getDesc();

        return result;
    }

    public String getCost() {
        Double salt = 2.0;
        System.out.println(String.format("加盐多收%s块", salt));
        String result = food.getCost() + " + " + salt + "块";

        return result;
    }
}
