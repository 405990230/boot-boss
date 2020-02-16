package com.boss.thread.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * 这里需要注意一下，try catch到InterruptedException e异常时，中断信号会被抹除，
 * 所以th.isInterrupted()如果执行在catch异常前，则isInterrupted为true，可以正常退出，否则中断信号抹除后，
 * isInterrupted得到的值为false，故还是死循环
 */
public class ThreadInterruptLuban {
    static Thread th=new Thread();
    public static boolean flag=false;
    public static void main(String[] args) {
        th = new Thread(() -> {
            while (!flag) {
                try {
                    System.out.println("im in while true");
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("-------"+th.isInterrupted());
                }
                System.out.println("-----next");



                System.out.println(th.isInterrupted());
            }
        });
        th.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        th.interrupt();
        flag=th.isInterrupted();
        System.out.println("flag : "+flag);
    }

}
