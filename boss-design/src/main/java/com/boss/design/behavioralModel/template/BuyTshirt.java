package com.boss.design.behavioralModel.template;

public class BuyTshirt extends ShoppingTemplate {
    @Override
    public void placingOrder() {
        System.out.println("我要下订单买鞋");
    }

    @Override
    public void reduceStock() {
        System.out.println("去鞋库拿鞋");
    }

    @Override
    public void pay() {
        System.out.println("支付宝支付买鞋的钱");
    }
}
