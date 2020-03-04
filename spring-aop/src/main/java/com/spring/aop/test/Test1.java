package com.spring.aop.test;

import com.spring.aop.dao.Dao;
//import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;

public class Test1 {
    /**
     * @EnableAspectJAutoProxy(proxyTargetClass = false)
     * 此时默认使用的是JDK动态代理，基于接口反射
     * 原因是因为Java是单继承的，JDK动态代理时，底层会继承Proxy类，故无法再继承其他类，
     * 只能基于接口实现JDK代理，只能基于接口实现发射，这就造成了当前对象和目标对象不同
     * cglib是基于父类来反射的
     * @param args
     */
    public static void main(String[] args) {
        Class<?> [] interfaces = new Class[]{Dao.class};
        byte bytes[] = null;//ProxyGenerator.generateProxyClass("LubanAa",interfaces);
        File file = new File("/Users/qxr4383/Documents/work/myself/code/luban.class");
        try {
            FileOutputStream fw = new FileOutputStream(file);
            fw.write(bytes);
            fw.flush();
            fw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
