package com.boss.design.behavioralModel.strategy.payport;

/**
 * 银联支付
 */
public class UnionPay implements Payment {

    public String pay(String uid,double amount) {
        System.out.println("欢迎使用银联卡");
        System.out.println("查询账户余额，开始扣款");
        return "支付成功，扣款："+ amount;
    }
}
