package com.boss.javabasic.ali;

import java.util.*;

/**
 *
 * 2. 红包算法，给定一个红包总金额和分红包的人数，输出每个人随机抢到的红包数量。
 * 要求：
 * 1. 每个人都要抢到红包，并且金额随机
 * 2. 每个人抢到的金额数不小于1
 * 3. 每个人抢到的金额数不超过总金额的30%
 * 例如总金额100，人数10，输出【19 20 15 1 25 14 2 2 1 1】
 *
 */
public class Test2 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(parseNum(10,3)));
    }

    public static double[] parseNum(double total,int num){
        double[] arrRandom = new double[num];
        //总金额
        double count = 0;
        Random roRandom = new Random();
        // 每人获得钱数
        List<Double> arrMoney = new ArrayList<>(num);

        //循环人数 随机点
        for(int i = 0;i <arrRandom.length;i++){
            int r = roRandom.nextInt((num)*99)+1;
            count += r;
            arrRandom[i] = r;
        }
        // 计算每人拆红包获得金额
        int c = 0;
        for(int i = 0;i <arrRandom.length;i++){
            // 每人获得随机数相加 计算每人占百分比
            Double x = new Double(arrRandom[i] / count);
            // 每人通过百分比获得金额
            Double m = Math.floor(x * total);
            // 如果获得 0 金额，则设置最小值 1分钱
            if (m == 0) {
                m = 1.00;
            }
            // 计算获得总额
            c += m;
            // 如果不是最后一个人则正常计算
            if (i < arrRandom.length - 1) {
                arrMoney.add(m);
            } else {
                // 如果是最后一个人，则把剩余的钱数给最后一个人
                arrMoney.add(total - c + m);
            }
        }
        // 随机打乱每人获得金额
        Collections.shuffle(arrMoney);
        return arrRandom;
    }
}
