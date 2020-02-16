package com.boss.design.creationMode.builder;

/**
 * Created by yd on 2019/3/26.
 * 装扮由鞋子、裤子、上衣组成
 */
public class PersonDress {
    //鞋子
    private String shoes;
    //裤子
    private String trousers;
    //上衣
    private String jacket;

    public String getShoes() {
        return shoes;
    }

    public void setShoes(String shoes) {
        this.shoes = shoes;
    }

    public String getTrousers() {
        return trousers;
    }

    public void setTrousers(String trousers) {
        this.trousers = trousers;
    }

    public String getJacket() {
        return jacket;
    }

    public void setJacket(String jacket) {
        this.jacket = jacket;
    }
}
