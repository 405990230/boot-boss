package com.boss.design.structuralModel.flyWeight;

/**
 * 设计模式 结构型模式 之 享元模式
 * 测试类
 */
public class MainTest {
    public static void main(String[] args) {
        Book book1 = Library.getBook("JAVA基础");
        book1.borrow(new Student("小明"));
        Book book3 = Library.getBook("JAVA高并发");
        book3.borrow(new Student("小红"));

        System.out.println("-----书籍还回图书馆后");

        Book book2 = Library.getBook("JAVA基础");
        book2.borrow(new Student("小黑"));

        // 测试是否是同一个对象
        System.out.println("小明和小黑借的书是否为同一本书：" + (book1 == book2));
        // 工厂类中享元池中对象数量
        System.out.println("图书馆时间借出了 ：" + Library.getSize() + " 本书");
    }
}
