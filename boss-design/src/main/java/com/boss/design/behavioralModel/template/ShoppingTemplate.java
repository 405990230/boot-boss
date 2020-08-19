package com.boss.design.behavioralModel.template;

/**
 * 模版模式
 */
public abstract class ShoppingTemplate {
    protected void buy(){
        this.placingOrder();
        this.reduceStock();
        this.pay();
        System.out.println("购买成功");
    }

    /**
     * 下订单
     */
    public abstract void placingOrder();


    /**
     * 减库存
     */
    public abstract void reduceStock();

    /**
     * 支付
     */
    public abstract void pay();

}
