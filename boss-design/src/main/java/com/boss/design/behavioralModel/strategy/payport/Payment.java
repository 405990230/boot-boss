package com.boss.design.behavioralModel.strategy.payport;

public interface Payment {
    String pay(String uid, double amount);
}
