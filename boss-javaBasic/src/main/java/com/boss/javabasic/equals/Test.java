package com.boss.javabasic.equals;

public class Test {
    public static void main(String[] args) {
        Person person = new Person("YD",27);
        System.out.println("p1:" +person);

        Person p2 = person;
        System.out.println("p2 :"+p2);
        person.setName("YYY");
        System.out.println("p2 :"+p2);
        p2.setName("ZZZZ");
        System.out.println("p1:" +person);

        System.out.println(p2==person);
        System.out.println(p2.equals(person));
    }
}
