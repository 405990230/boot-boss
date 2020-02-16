package com.boss.thread.jion.java8;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by qxr4383 on 2019/6/6.
 */
public class TestJoin {

    public static void main(String[] args) throws InterruptedException {
        //定义两线程，并保存在threads中
        List<Thread> threads = IntStream.range(1, 3).mapToObj(TestJoin::create).collect(Collectors.toList());


        //启动这两个线程
        //threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.start();
            thread.join();
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "#" + i);
            shortSleep();
        }
    }

    private static Thread create(int seq) {
        return new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "#" + i);
                shortSleep();
            }
        });
    }

    private static void shortSleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
