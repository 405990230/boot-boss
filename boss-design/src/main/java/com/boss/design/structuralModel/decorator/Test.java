package com.boss.design.structuralModel.decorator;

/**
 * Created by qxr4383 on 2019/4/2.
 */
public class Test {
    public static void main(String[] args) {
        //创建不用装饰器修饰的Food
        Food food = new BarbecueFood();
        display(food.getDesc());
        display(food.getCost());
        System.out.println("-------------分割线---------------");
        //创建用SaltFoodDecorator装饰的Food
        Food barbecue = new SaltFoodDecorator(new BarbecueFood());
        display(barbecue.getDesc());
        display(barbecue.getCost());
        System.out.println("-------------分割线---------------");
        //创建用PepperFoodDecorator装饰的Food
        Food hotSpot = new PepperFoodDecorator(new HotpotFood());
        display(hotSpot.getDesc());
        display(hotSpot.getCost());
    }

    private static void display(Object obj) {
        System.out.println(obj);
    }

}
