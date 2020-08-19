package com.boss.design.behavioralModel.strategy;

import com.boss.design.behavioralModel.strategy.payport.AliPay;
import com.boss.design.behavioralModel.strategy.payport.PayType;
import com.boss.design.behavioralModel.strategy.payport.UnionPay;
import com.boss.design.behavioralModel.strategy.payport.WeChatPay;

public class PayStrategyTest {
    public static void main(String[] args) throws Exception{
        //下单
        Order order = new Order("1","20200311",100);
        PayContext payContext = null;
        //开始支付，选择支付方式：支付宝 ALI_PAY、银联卡 UNION_PAY、微信支付 WECHAT_PAY
        //每个渠道它的支付的具体算法不一样
        String payType = "WECHAT_PAY";
        if("ALI_PAY".equals(payType)){
            payContext = new PayContext(new AliPay());
        } else if("WECHAT_PAY".equals(payType)){
            payContext = new PayContext(new WeChatPay());
        } else if("UNION_PAY".equals(payType)){
            payContext = new PayContext(new UnionPay());
        } else {
            throw new Exception("支付异常");
        }
        System.out.println(payContext.pay(order.getUid(),order.getAmount()));
    }
}
