package com.boss.thread.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class VectorTest {
    private static Vector<Integer> vec = new Vector<>();
    private static List<Integer> lst = new ArrayList<>();


    public static void main(String[] args) {
        for(int j =0 ;j<2;j++){
            new Thread(()->{
                for (int i = 0; i < 1000; ++i) {
                    vec.add(i);
                    lst.add(i);
                }
            }).start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("vec size is " + vec.size());
        System.out.println("lst size is " + lst.size());

    }
}
