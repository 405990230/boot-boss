package com.boss.syn;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * 打印Java对象头
 */
public class JoLExample {
    public static void main(String[] args) throws Exception {
        A a = new A();
        //jvm的信息
        System.out.println(VM.current().details());
        System.out.println("-------------------------");
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }
}
