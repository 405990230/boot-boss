package com.boss.design;

import java.util.Random;

public enum CountNameEum {
    NEW_ACCOUNT_TODAY ("newAccountToday"),
    ACCOUNT_COUNT("accountCount"),
    ACTIVE_ACCOUNT_COUNT("activeAccountCount"),
    ACTIVE_ACCOUNT_COUNT_TODAY("activeAccountCountToday");

    private String value;

    private CountNameEum(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static void main(String[] args) {

        System.out.println(CountNameEum.ACCOUNT_COUNT.getValue());

        Random r = new Random();
        while(true){
            System.out.println(r.nextInt(100000));
        }
    }
}
