package com.boss.syn;
import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;

public class JOLExample13 {
   static A a;
    public static void main(String[] args) throws Exception {
        a= new A();
        Thread t1 = new Thread(){
            @Override
            public void run() {
                synchronized (a){
                    out.println("t1 lock ing");
                    out.println(ClassLayout.parseInstance(a).toPrintable());
                }
            }
        };
        t1.start();
        t1.join();
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                synchronized (a){
                    out.println("t2 lock ing");
                    out.println(ClassLayout.parseInstance(a).toPrintable());
                }
            }
        };
        thread2.start();
        out.println("after t2 lock");
        out.println(ClassLayout.parseInstance(a).toPrintable());
        thread2.join();
        Thread thread3 = new Thread(){
            @Override
            public void run() {
                synchronized (a){
                    out.println("t3 lock ing");
                    out.println(ClassLayout.parseInstance(a).toPrintable());
                }
            }
        };
        thread3.start();
        out.println(ClassLayout.parseInstance(a).toPrintable());

    }
}
