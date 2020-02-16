package com.boss.syn;

import org.openjdk.jol.info.ClassLayout;
import static java.lang.System.out;

/**
 * 需要注意的是如果对象已经计算了hashcode就不能偏向了
 */
public class JOLExample8 {
    static C c;
    public static void main(String[] args) throws Exception {
        Thread.sleep(5000);
        c= new C();
        c.hashCode();
        out.println("befor lock");
        out.println(ClassLayout.parseInstance(c).toPrintable());
        synchronized (c){
            out.println("lock ing");
            out.println(ClassLayout.parseInstance(c).toPrintable());
        }
        out.println("after lock");
        out.println(ClassLayout.parseInstance(c).toPrintable());
    }
}
