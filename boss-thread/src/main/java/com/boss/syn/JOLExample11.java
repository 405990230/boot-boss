package com.boss.syn;
import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;

public class JOLExample11 {
    static A a;
    public static void main(String[] args) throws Exception {
        //Thread.sleep(5000);
        a = new A();
        out.println("befre lock");
        //无锁
        out.println(ClassLayout.parseInstance(a).toPrintable());

        Thread t1= new Thread(){
            public void run() {
                synchronized (a){
                    try {
                        synchronized (a) {
                            System.out.println("before wait");
                            //轻量级锁
                            out.println(ClassLayout.parseInstance(a).toPrintable());
                            a.wait();
                            System.out.println(" after wait");
                            //重量级锁
                            out.println(ClassLayout.parseInstance(a).toPrintable());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();
        Thread.sleep(7000);
        synchronized (a) {
            a.notifyAll();
        }
    }
}