package com.boss.syn;

public class C {
    int i=0;
    // boolean flag =false;
    public synchronized void parse(){
        i++;
        JOLExample6.countDownLatch.countDown();
    }
}
