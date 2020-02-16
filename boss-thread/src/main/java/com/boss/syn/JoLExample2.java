package com.boss.syn;

import org.openjdk.jol.info.ClassLayout;

public class JoLExample2 {
    public static void main(String[] args) throws Exception {
        A a= new A();
        System.out.println("befor hash");
        //没有计算HASHCODE之前的对象头
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
        //JVM 计算的hashcode
        System.out.println("jvm‐‐‐‐‐‐‐‐‐‐‐‐0x"+Integer.toHexString(a.hashCode()));
        HashUtil.countHash(a);
        //当计算完hashcode之后，我们可以查看对象头的信息变化
        System.out.println("after hash");
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }
}
