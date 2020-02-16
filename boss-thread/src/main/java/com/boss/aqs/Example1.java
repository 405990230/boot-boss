package com.boss.aqs;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch
 */
public class Example1 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(15);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0;i<15;i++){
            final int finalI = i ;
            executorService.execute(()->{
                try{
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(finalI);
                    latch.countDown();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }

        latch.await(2,TimeUnit.SECONDS);
        System.out.println("end");
    }
}
