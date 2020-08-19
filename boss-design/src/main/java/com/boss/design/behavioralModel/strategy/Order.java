package com.boss.design.behavioralModel.strategy;


import lombok.Data;

/**
 * 订单类
 */
@Data
public class Order {
    private String uid;
    private String orderId;
    private double amount;

    public Order(String uid, String orderId, double amount) {
        this.uid = uid;
        this.orderId = orderId;
        this.amount = amount;
    }
}
