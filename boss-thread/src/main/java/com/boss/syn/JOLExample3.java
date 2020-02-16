package com.boss.syn;

import org.openjdk.jol.info.ClassLayout;

/**
 * 偏向锁
 */
public class JOLExample3 {
    static A a;
    public static void main(String[] args) throws Exception {
        //关闭偏向锁延时
        //-XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
        Thread.sleep(5000);
        a= new A();

        //a.hashCode();
        System.out.println("befor lock");
        System.out.println(ClassLayout.parseInstance(a).toPrintable());

        synchronized (a){
            System.out.println("lock ing");
            System.out.println(ClassLayout.parseInstance(a).toPrintable());
        }
        System.out.println("after lock");
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }
}
