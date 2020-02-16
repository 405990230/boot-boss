package com.boss.design.structuralModel.decorator;

/**
 * Created by qxr4383 on 2019/4/2.
 * FoodDecorator是一个抽象类，其中组合Food类
 */
public abstract class FoodDecorator implements Food {
    protected Food food;

    public FoodDecorator(Food food) {
        this.food = food;
    }

    public String getDesc() {
        return food.getDesc();
    }

    public String getCost() {
        return food.getCost();
    }
}
