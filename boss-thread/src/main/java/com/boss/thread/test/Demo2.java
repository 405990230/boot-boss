package com.boss.thread.test;

import java.util.concurrent.TimeUnit;

public class Demo2 {
    String s1 = "hello";
    String s2 = "hello";

    public void test(){
        synchronized (s1){
            System.out.println("t1 start---------");
            try {
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("t1 end---------");
        }
    }

    public void test2(){
        synchronized (s2){
            System.out.println("t2 start----------");
        }
    }

    public static void main(String[] args) {
        Demo2 demo2 = new Demo2();
        new Thread(demo2 :: test,"t1").start();
        new Thread(demo2 :: test2,"t2").start();
    }
}
