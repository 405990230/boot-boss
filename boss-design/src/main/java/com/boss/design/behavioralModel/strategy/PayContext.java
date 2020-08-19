package com.boss.design.behavioralModel.strategy;

import com.boss.design.behavioralModel.strategy.payport.Payment;

public class PayContext {
    private Payment payment;

    public PayContext(Payment payment){
        this.payment = payment;
    }

    public String pay(String uid, double amount){
        return payment.pay(uid,amount);
    }
}
