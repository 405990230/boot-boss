package com.boss.design.behavioralModel.strategy.payport;

/**
 * 微信支付
 */
public class WeChatPay implements Payment {

    public String pay(String uid,double amount) {
        System.out.println("欢迎使用微信支付");
        System.out.println("查询账户余额，开始扣款");
        return "支付成功，扣款："+ amount;
    }
}
