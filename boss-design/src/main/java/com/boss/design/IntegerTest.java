package com.boss.design;

import java.util.ArrayList;
import java.util.List;

public class IntegerTest {
    public static void main(String[] args) {
        Integer a =5;
        Integer b =1000;
        Integer c = 2000;
        Integer d= 105;
        List<Integer> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);

        a = a+100;
        b = 1000+100;
        c = Integer.valueOf(2008);
        System.out.println(a+":"+list.get(0));
        System.out.println(b+":"+list.get(1));
        System.out.println(c+":"+list.get(2));
        list.set(0,105);
        System.out.println(a==list.get(0));
        System.out.println(b==list.get(1));
        System.out.println(a==d);
    }
}
