package com.boss.syn;

/**
 * 对象A
 */
public class A {
    boolean flag =false;
    int i=0;
    public synchronized void parse(){
        i++;
    }
}
