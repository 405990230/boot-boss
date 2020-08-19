package com.boss.javabasic.condition;

public class Condition {

    public static void main(String[] args) {
        get(() -> testMethod());
        insert(() -> testMethod());
    }

    public static <T> T get(ICallable<T> callable) {
        return callable.call();
    }

    public static void insert(ICallable callable) {
        callable.call();
    }

    @FunctionalInterface
    public interface ICallable<V> {
        V call();
    }

    public static int testMethod(){
        System.out.println("testMethod");
        return 2;
    }

}
