package com.boss.bank;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private Lock bankLock;
    Condition sufficientFunds;
    private final double[] accouts;

    public Bank(int n,double initialBalance){
        accouts = new double[n];
        for(int i = 0;i<accouts.length;i++){
            accouts[i] = initialBalance;
        }
        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();
    }

    public void transfer(int from,int to,double amount){
        bankLock.lock();
        try{
            while(accouts[from]<amount){
                sufficientFunds.await();
            }
            System.out.print(Thread.currentThread());
            accouts[from] -= amount;
            System.out.printf("%10.2f from %d to %d",amount,from,to);
            accouts[to] += amount;
            System.out.printf(" Total Balance : %10.2f%n",getTotalBalance());
            getBalance();
            sufficientFunds.signalAll();

        } catch (InterruptedException e){

        } finally {
            bankLock.unlock();
        }

    }

    public double getTotalBalance(){
        double sum = 0;
        for(double a :accouts)
            sum+=a;
            return sum;
    }

    public double getBalance(){
        for(double a :accouts)
            if (a<0) {
            System.out.println("-----------"+a);
                return a;
            }
        return 0;
    }


    public int size(){
        return accouts.length;
    }
}
