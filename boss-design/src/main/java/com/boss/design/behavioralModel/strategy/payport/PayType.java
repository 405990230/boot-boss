package com.boss.design.behavioralModel.strategy.payport;

/**
 * 枚举当作一个常量去维护
 */
public enum PayType {
    ALI_PAY(1,new AliPay()),
    WECHAT_PAY(2,new WeChatPay()),
    UNION_PAY(3,new UnionPay());

    private int code;
    private Payment payment;


    PayType(int code,Payment payment){
        this.code = code;
        this.payment = payment;
    }

    public Payment getValue(){
        return this.payment;
    }
    public int getCode(){
        return this.code;
    }

    public static Payment getPayment(int code) {
        for (PayType payType : PayType.values()) {
            if (code == payType.getCode()) {
                return payType.getValue();
            }
        }
        return null;
    }
}
