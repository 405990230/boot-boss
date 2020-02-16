package com.boss.aqs;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读读并行
 * 读写窜行
 * 写写窜行
 */
public class RwExample {
    ReadWriteLock rwlock = new ReentrantReadWriteLock();
    private Integer data = 0;

    public void get(){
        rwlock.readLock().lock();
        System.out.println(Thread.currentThread().getName()+"ready r");
        try {
            Thread.sleep((long)Math.random()*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"读到数据等于"+data);
        rwlock.readLock().unlock();
    }

    public void put(Integer data){
        rwlock.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+"write w");
        try {
            Thread.sleep((long)Math.random()*1000);
            this.data = data;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"写入数据等于"+data);
        rwlock.writeLock().unlock();
    }

    public static void main(String[] args) {
        RwExample rwExample = new RwExample();
        for(int i = 0;i<10;i++){
            new Thread(()->rwExample.get()).start();//r
            new Thread(()->rwExample.put(new Random().nextInt(10))).start();
        }
    }
}
