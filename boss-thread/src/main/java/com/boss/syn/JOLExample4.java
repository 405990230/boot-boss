package com.boss.syn;

public class JOLExample4 {
    /**
     * 轻量级锁：22500ms
     * 偏向锁：2408ms
     * @param args
     * @throws Exception
     */
    //关闭偏向锁延时
    //-XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
    public static void main(String[] args) throws Exception {
        A b = new A();
        long start = System.currentTimeMillis();
        //调用同步方法1000000000L 来计算1000000000L的++，对比偏向锁和轻量级锁的性能
        //如果不出意外，结果灰常明显
        for(int i=0;i<1000000000L;i++){
            b.parse();
        }
        long end = System.currentTimeMillis();
        System.out.println(String.format("%sms", end - start));

    }
}