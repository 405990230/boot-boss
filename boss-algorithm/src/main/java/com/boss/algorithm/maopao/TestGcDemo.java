package com.boss.algorithm.maopao;


public class TestGcDemo {
    public static void main(String[] args) {
        String str = "";
        for (int x = 0; x < Integer.MAX_VALUE; x++) {
            str += x + str;
            str.intern();
        }
    }
}
