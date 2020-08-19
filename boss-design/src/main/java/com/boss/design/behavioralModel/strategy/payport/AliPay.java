package com.boss.design.behavioralModel.strategy.payport;

/**
 * 支付宝pay
 */
public class AliPay implements Payment {

    public String pay(String uid,double amount) {
        System.out.println("欢迎使用支付宝");
        System.out.println("查询账户余额，开始扣款");
        return "支付成功，扣款："+ amount;
    }
}
