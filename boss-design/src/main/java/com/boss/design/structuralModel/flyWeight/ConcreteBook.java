package com.boss.design.structuralModel.flyWeight;

/**
 * 具体享元类
 */
public class ConcreteBook implements Book {
    //书名
    private String bookName;

    public ConcreteBook(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public void borrow(Student student) {
        System.out.println(student.getName() + "---- 他借走了这本书是 ：" + this.bookName);
    }
}
