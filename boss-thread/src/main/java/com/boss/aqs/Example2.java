package com.boss.aqs;

import java.util.concurrent.*;

public class Example2 {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0;i<15;i++){
            final int finalI = i ;
            executorService.execute(()->{
//                try{
//                    semaphore.acquire();
//                    test(finalI);
//                    semaphore.release();
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }

                try{
                    if(semaphore.tryAcquire(3)){
                        test(finalI);
                        semaphore.release(3);
                    }

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }

    public static void test(int k) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(k+"--------"+System.currentTimeMillis());
    }
}
