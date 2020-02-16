package com.boss.design.structuralModel.facade;

import com.boss.design.structuralModel.facade.facade.Computer;

/**
 * 设计模式 结构型模式 之 外观模式
 */
public class Client {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.start();
        System.out.println("--------------------------");
        computer.shutDown();
    }
}
