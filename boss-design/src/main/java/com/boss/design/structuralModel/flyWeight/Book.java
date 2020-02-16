package com.boss.design.structuralModel.flyWeight;

/**
 * 借书 -- 抽象享元类
 */
public interface Book {
    /**
     * 借书--享元类公共方法
     *
     * @param student
     */
    void borrow(Student student);
}
