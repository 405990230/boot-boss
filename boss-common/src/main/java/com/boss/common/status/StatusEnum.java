package com.boss.common.status;

public enum StatusEnum {
    SUCCESS("success"),
    INVALID("invalid"),
    FAIL("fail");


    private String value;

    StatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
