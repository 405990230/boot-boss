package com.boss.syn;

import org.openjdk.jol.info.ClassLayout;

/**
 * 轻量锁
 */
public class JOLExample5 {
    static A a;
    public static void main(String[] args) throws Exception {
        a = new A();
        System.out.println("befre lock");
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
        sync();
        System.out.println("after lock");
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }

    public  static  void sync() throws InterruptedException {
        synchronized (a){
            System.out.println("lock ing");
            System.out.println(ClassLayout.parseInstance(a).toPrintable());
        }
    }
}
