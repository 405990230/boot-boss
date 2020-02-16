package com.boss.javabasic.list;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListExceptionTest {

    /**
     * ConcurrentModificationException复现方法一
     */
    public static void listRemoveExcption() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            arrayList.add(Integer.valueOf(i));
        }
        // 复现方法一
        Iterator<Integer> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer.intValue() == 2) {
                arrayList.remove(integer);
            }
            System.out.println("---复现方法一：" + integer);
        }
    }

    /**
     * ConcurrentModificationException复现方法二
     */
    public static void listRemoveExcption2() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(Integer.valueOf(i));
        }
        //复现方法二
        Iterator<Integer> it = list.iterator();
        for (Integer value : list) {
            Integer integer = it.next();
            if (integer.intValue() == 2) {
                list.remove(integer);
            }
            System.out.println("---复现方法二：" + integer);
        }
    }

    public static void listRemove() {
        ArrayList<Integer> arry = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            arry.add(Integer.valueOf(i));
        }
        //解决方法-
        Iterator<Integer> iterator = arry.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer.intValue() == 2) {
                iterator.remove();
            }
            System.out.println("---解决方法：" + integer);
        }
    }

    public static void listOperation() {
        ArrayList<Integer> arry = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            arry.add(Integer.valueOf(i));
        }

        ListIterator<Integer> iterator = arry.listIterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer.intValue() == 5) {
                iterator.set(Integer.valueOf(6));
                iterator.remove();
                iterator.add(integer);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("----------复现方法一------------");
        listRemoveExcption();

        System.out.println("----------复现方法二------------");
        listRemoveExcption2();

        System.out.println("----------解决方法------------");
        listRemove();
        List<Integer> list = new CopyOnWriteArrayList<>();
    }
}
