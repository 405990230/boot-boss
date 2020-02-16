package com.boss.thread.interrupt;

import org.apache.poi.ss.formula.functions.T;

import java.util.concurrent.TimeUnit;

/**
 * Created by yd on 2019/5/30.
 */
public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("Oh, I am be interrupted");
                }
            }
        };
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();
    }
}
