package com.boss.javabasic.equals;

public class EqualTes {
    public static void main(String[] args) {
        String a = "hello";
        String b = "hello";
        System.out.println(a == b);//true
        System.out.println(a.equals(b));//true

        String c = new String("hello");
        String d = new String("hello");
        System.out.println(c + ":"+ d);
        System.out.println(a == c);//false
        System.out.println(c == d);//false
        System.out.println(c.equals(d));//true


        System.out.println("----------------");
        Integer i = 100;
        Integer j = 100;
        System.out.println(i == j);//true

        Integer k = 1000;
        Integer m = 1000;
        System.out.println(k == m);//false
    }
}
