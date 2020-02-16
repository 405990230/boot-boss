package com.boss.thread.test;

import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TicketDemo3 {
    private static Queue<Integer> tickets = new ConcurrentLinkedQueue<>();
    static{
        for(int i =0;i<10000;i++){
            tickets.add(i);
        }
    }

    public static void main(String[] args) {
        for(int i =0;i<10;i++){
            new Thread(()->{
                while (true){
                    Integer poll = tickets.poll();
                        if(poll==null){
                            break;
                        }
                        System.out.println(Thread.currentThread().getName()+"销售票号："+poll);

                }
            }).start();
        }
    }
}
