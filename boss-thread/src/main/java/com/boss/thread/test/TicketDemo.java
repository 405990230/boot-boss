package com.boss.thread.test;

import java.util.ArrayList;
import java.util.List;

public class TicketDemo {
    private static List<Integer> tickets = new ArrayList<>();
    static{
        for(int i =0;i<10000;i++){
            tickets.add(i);
        }
    }

    public static void main(String[] args) {
        for(int i =0;i<10;i++){
            new Thread(()->{
                while (tickets.size()>0){
                    System.out.println(Thread.currentThread().getName()+"销售票号："+tickets.remove(0));
                }
            }).start();
        }
    }
}
