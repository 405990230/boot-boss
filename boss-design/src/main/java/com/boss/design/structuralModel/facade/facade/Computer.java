package com.boss.design.structuralModel.facade.facade;

import com.boss.design.structuralModel.facade.children.CPU;
import com.boss.design.structuralModel.facade.children.Disk;
import com.boss.design.structuralModel.facade.children.Memory;

/**
 * 门面类（核心）
 */
public class Computer {
    private CPU cpu;
    private Memory memory;
    private Disk disk;

    public Computer() {
        cpu = new CPU();
        memory = new Memory();
        disk = new Disk();
    }

    public void start() {
        System.out.println("computer start begin.....");
        cpu.start();
        memory.start();
        disk.start();
        System.out.println("computer start end.....");
    }

    public void shutDown() {
        System.out.println("computer shutDown begin.....");
        cpu.start();
        memory.start();
        disk.start();
        System.out.println("computer shutDown end.....");
    }
}
