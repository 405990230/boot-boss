package com.boss.design.behavioralModel.strategy;

import com.boss.design.behavioralModel.strategy.payport.PayType;
public class PayTest {
    public static void main(String[] args) throws Exception{
        //下单
        Order order = new Order("1","20200311",100);
        //开始支付，选择支付方式：支付宝 ALI_PAY、银联卡 UNION_PAY、微信支付 WECHAT_PAY
        //每个渠道它的支付的具体算法不一样
        int payType = 1;
        PayContext payContext = new PayContext(PayType.getPayment(payType));
        System.out.println(payContext.pay(order.getUid(),order.getAmount()));
    }
}
