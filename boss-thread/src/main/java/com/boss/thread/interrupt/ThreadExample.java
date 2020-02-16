package com.boss.thread.interrupt;

import java.util.ArrayList;
import java.util.List;

public class ThreadExample {
    List lists = new ArrayList();
    static Thread thread = null;
    public static  boolean running = true;
    public static void main(String[] args) throws InterruptedException{
        traditional();
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }

    public static void traditional() throws InterruptedException{
        thread = new Thread(){
            @Override
            public void run(){
                while (!this.isInterrupted()){
                    System.out.println("while----");

                    try {
                        Thread.sleep(2000);

                    }catch (InterruptedException e){
                        //解阻塞
                        e.printStackTrace();
                        System.out.println(this.isInterrupted());
                    }
                    System.out.println("next ---");
                }
            }
        };
    }
}
