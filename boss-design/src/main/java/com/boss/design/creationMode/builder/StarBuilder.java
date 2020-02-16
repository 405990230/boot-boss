package com.boss.design.creationMode.builder;

/**
 * Created by yd on 2019/3/26.
 * 具体穿衣搭配
 */
public class StarBuilder implements PersonBuilder {

    private PersonDress personDress;

    public StarBuilder() {
        personDress = new PersonDress();
    }

    public void buildShoes() {
        personDress.setShoes("穿古驰的鞋");
    }

    public void buildTrousers() {
        personDress.setTrousers("穿LV的裤子");
    }

    public void buildJacket() {
        personDress.setJacket("穿阿玛尼的T恤");
    }

    public PersonDress buildPersonDress() {
        return personDress;
    }
}
