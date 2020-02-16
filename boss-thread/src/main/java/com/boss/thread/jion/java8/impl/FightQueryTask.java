package com.boss.thread.jion.java8.impl;

import com.boss.thread.jion.java8.FightQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created by qxr4383 on 2019/6/5.
 */
public class FightQueryTask extends Thread implements FightQuery {
    private final String origin;
    private final String destination;
    private final List<String> flightList = new ArrayList<>();

    public FightQueryTask(String airline, String origin, String destination) {
        super("[" + airline + "]");
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public void run() {
        int randomVal = ThreadLocalRandom.current().nextInt(10);
        System.out.printf("%s-query from %s to %s ,%s\n", getName(), origin, destination, randomVal);
        try {
            TimeUnit.SECONDS.sleep(randomVal);
            this.flightList.add(getName() + "-" + randomVal);
            System.out.printf("The Fight:%s list query successful\n", getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> get() {
        return this.flightList;
    }
}
