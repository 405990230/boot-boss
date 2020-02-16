package com.boss.thread.jion;

/**
 * Created by qxr4383 on 2019/6/6.
 */
public class TestJoin {
    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        ThreadTest t1 = new ThreadTest("A");
        ThreadTest t2 = new ThreadTest("B");
        t1.start();
        t2.start();
        //t1执行join方法后，主线程进入等待池并等待t线程执行完毕后才会被唤醒，
        t1.join();
        t2.join();
//        for (int i=0;i<5;i++){
//            System.out.println(Thread.currentThread().getName()+"#"+i);
//        }
    }
}

class ThreadTest extends Thread {
    private String name;

    public ThreadTest(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(name + "-" + i);
        }
    }
}
