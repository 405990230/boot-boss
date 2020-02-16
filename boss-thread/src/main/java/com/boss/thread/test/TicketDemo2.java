package com.boss.thread.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class TicketDemo2 {
    private static Vector<Integer> tickets = new Vector<>();
    static{
        for(int i =0;i<10000;i++){
            tickets.add(i);
        }
    }

    public static void main(String[] args) {
        for(int i =0;i<10;i++){
            new Thread(()->{
//                while (true){
//                    synchronized (tickets){
//                        if(tickets.size()<=0){
//                            break;
//                        }
//                        System.out.println(Thread.currentThread().getName()+"销售票号："+tickets.remove(tickets.size()-1));
//                    }
//                }
                while (tickets.size()>0){
                    System.out.println(Thread.currentThread().getName()+"销售票号："+tickets.remove(tickets.size()-1));

                }
            }).start();
        }
    }
}
