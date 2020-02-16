package com.boss.thread.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * Created by qxr4383 on 2019/5/30.
 */
public class ThreadidInterrupted3 {
    public static void main(String[] args) throws InterruptedException {
        //1、判断当前线程是否被中断
        System.out.println("main is interrupted ? " + Thread.interrupted());
        //2、中断当前线程
        Thread.currentThread().interrupt();
        //3、判断当前线程是否被已经中断
        System.out.println("main is isInterrupted ? " + Thread.currentThread().isInterrupted());

        //线程发生中断，第一次调用，返回true，并擦除标识
        System.out.println("main is interrupted ? " + Thread.interrupted());
        //第二次false
        System.out.println("main is interrupted ? " + Thread.interrupted());
        try {
            //4、当前线程 执行可中断
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            //因为interrupted()擦除了中断标识，所以无法进入捕获异常处理
            //5、捕获中断信号
            System.out.printf("I will be interrupted still.");
        }
    }
}
