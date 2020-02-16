package com.boss.javabasic.equals;

import lombok.Data;

@Data
public class Person {
    static volatile int i=5;
    private String name;
    private int age;

    public Person(String name,int age){
        this.name = name;
        this.age = age;
    }
}
