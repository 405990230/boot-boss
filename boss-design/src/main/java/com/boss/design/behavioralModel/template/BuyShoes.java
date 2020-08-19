package com.boss.design.behavioralModel.template;

public class BuyShoes extends ShoppingTemplate {
    @Override
    public void placingOrder() {
        System.out.println("我要下订单买衣服");
    }

    @Override
    public void reduceStock() {
        System.out.println("去衣服库拿鞋");
    }

    @Override
    public void pay() {
        System.out.println("微信支付买衣服的钱");
    }
}
