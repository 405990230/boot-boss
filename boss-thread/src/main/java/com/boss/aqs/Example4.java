package com.boss.aqs;

import java.util.concurrent.locks.ReentrantLock;

public class Example4 {
    public static void main(String[] args) {
        final ReentrantLock reentrantLock = new ReentrantLock(true);
        Thread t1 = new Thread(()->{
            System.out.println("before t1-----------");
            reentrantLock.lock();
            System.out.println("t1  ing-----------");
            reentrantLock.unlock();

        },"t1");
        t1.start();

        Thread t2 = new Thread(()->{
            System.out.println("before t2-----------");
            reentrantLock.lock();
            System.out.println("t2  ing-----------");
            reentrantLock.unlock();

        },"t2");
        t2.start();

//        Thread t3 = new Thread(()->{
//            System.out.println("before t3-----------");
//            reentrantLock.lock();
//            System.out.println("t3  ing-----------");
//            reentrantLock.unlock();
//
//        },"t3");
//        t3.start();

    }

    public static void logic(){
        System.out.println("----------");
    }
}
