package com.boss.algorithm.maopao;

public class ShowSpaceDemo {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("MAX_MEMBER:" + runtime.maxMemory() / 1024 / 1024);//最大可用内存
        System.out.println("TOTAL_MEMBER:" + runtime.totalMemory() / 1024 / 1024);//默认的可用内存
        System.out.println("FREE_MEMBER:" + runtime.freeMemory() / 1024 / 1024);//可用的内存中空闲的部分

    }
}
