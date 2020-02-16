package com.boss.javabasic.enums;

import java.util.Arrays;

public enum ColorEnum {
    ORANGE("橙色"),
    GREY("灰色"),
    GLODEN("金色"),
    BROWN("棕色"),
    SILVERY("银色");

    private String value;

    ColorEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static void main(String[] args) {
        String color = ColorEnum.GLODEN.getValue();
        String color2 = ColorEnum.GLODEN.value;
        System.out.println(color + "-----" + color2);

        ColorEnum[] list = ColorEnum.values();
        System.out.println(Arrays.asList(list));

        for (ColorEnum colorEnum : list) {
            System.out.println(colorEnum.value + "----" + colorEnum.getValue());
        }

    }
}


