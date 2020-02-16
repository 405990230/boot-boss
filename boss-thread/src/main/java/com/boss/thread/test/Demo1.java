package com.boss.thread.test;

import java.util.concurrent.TimeUnit;

public class Demo1 {
    Object o = new Object();
    public void test(){
        synchronized (o){
            System.out.println("3"+o);
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        System.out.println("1"+demo1.o);
        new Thread(demo1 :: test,"t1").start();
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        demo1.o = new Object();
        System.out.println("2"+demo1.o);
        new Thread(demo1 :: test,"t2").start();

    }
}
