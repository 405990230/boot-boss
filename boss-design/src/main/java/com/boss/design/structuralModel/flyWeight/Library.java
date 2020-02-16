package com.boss.design.structuralModel.flyWeight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂类
 */
public class Library {

    // map充当对象享元池  图书馆维护一个图书列表
    private static Map<String, Book> bookMap = new HashMap<>();

    //图书馆外借书
    public static Book getBook(String bookName) {
        // 从享元池中拿  书本
        Book concreteBook = bookMap.get(bookName);
        // 如果享元池中没有此对象 书
        if (concreteBook == null) {
            // 创建对象 书
            concreteBook = new ConcreteBook(bookName);
            // 存到享元池中
            bookMap.put(bookName, concreteBook);
        }
        // 返回对象 书
        return concreteBook;
    }

    public static int getSize() {
        return bookMap.size();
    }
}
