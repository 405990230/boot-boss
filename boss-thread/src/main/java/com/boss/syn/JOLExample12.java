package com.boss.syn;
import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class JOLExample12 {
    static List<A> list = new ArrayList<A>();
    public static void main(String[] args) throws Exception {
        Thread.sleep(5000);
        Thread t1 = new Thread() {
            public void run() {
                for (int i=0;i<40;i++){
                    A a = new A();
                    synchronized (a){
                        System.out.println("111111");
                        list.add(a);
                    }
                }

            }

        };
        t1.start();
        t1.join();
        out.println("befre t2");
        //偏向
        out.println(ClassLayout.parseInstance(list.get(1)).toPrintable());
        Thread t2 = new Thread() {
            int k=0;
            public void run() {
                for(A a:list){
                   synchronized (a){
                       System.out.println("22222");
                       if (k==10||k==18||k==19){
                           out.println("t2 ing----" +k);
                           //第19个对象轻量锁，第20个对象偏向锁
                           out.println(ClassLayout.parseInstance(a).toPrintable());

                       }
                   }
                    k++;
                }

            }

        };
        t2.start();
        t2.join();
        out.println("after t2");
        //无锁
        out.println(ClassLayout.parseInstance(list.get(18)).toPrintable());
        //偏向锁
        out.println(ClassLayout.parseInstance(list.get(19)).toPrintable());

    }
}