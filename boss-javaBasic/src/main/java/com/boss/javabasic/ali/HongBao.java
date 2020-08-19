package com.boss.javabasic.ali;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HongBao {

    public static void main(String[] args) {
        System.out.println( divideRedPackage(10,4));
        System.out.println( divideRedPackage2(100,6));
        System.out.println(allocateMoney(100,5));
    }

    /**
     * 拆分红包  -- 二倍均值法
     * @param totalAmount 总金额（以分为单位）
     * @param totalPeopleNum 总人数
     * @return
     */
     public static List<Integer> divideRedPackage(Integer totalAmount,Integer totalPeopleNum){
         List<Integer> amountList = new ArrayList<>();
         Integer restAmount =  totalAmount;
         Integer restPeopleAmount = totalPeopleNum;
         Random random = new Random();
         for(int i = 0;i<totalPeopleNum-1;i++){
             //随机范围：【1，剩余人均金额的2倍-1】分
             int amount = random.nextInt(restAmount / restPeopleAmount * 2 -1)+1;
             restAmount -= amount;
             restPeopleAmount--;
             amountList.add(amount);
         }
         amountList.add(restAmount);
         return amountList;
     }

    public static List<Integer> divideRedPackage2(Integer totalAmount,Integer totalPeopleNum) {
        List<Integer> list=new ArrayList<>();
        List<Integer> result=new ArrayList<>();
        Random random = new Random();

        while (list.size()<totalPeopleNum) {
            int i=random.nextInt(totalAmount-1)+1;//最低1分钱
            if(list.indexOf(i)<0){//非重复切割添加到集合
                list.add(i);
            }
        }
        Collections.sort(list);
        int  flag=0;
        int fl=0;
        for (int i=0;i<list.size();i++) {
            int temp=list.get(i)-flag;
            flag=list.get(i);
            fl+=temp;
            result.add(temp);
            //System.out.println("红包切割金额"+AmountUtil.div(temp,100));
        }
        //最后一个红包
        result.add(totalAmount-fl);
        //System.out.println("红包切割金额"+AmountUtil.div(AmountUtil.sub(totalAmount, fl),100));

        System.out.println(result.stream().mapToInt(p ->p).sum());
        return result;
    }


    //最少分得红包数
    private static final double min = 1;
    //最多分得红包数占比
    private static final double percentMax = 0.3;

    /**
     *
     * 红包算法，给定一个红包总金额和分红包的人数，输出每个人随机抢到的红包数量。
     * 要求：
     * 1. 每个人都要抢到红包，并且金额随机
     * 2. 每个人抢到的金额数不小于1
     * 3. 每个人抢到的金额数不超过总金额的30%
     * 例如总金额100元，人数10，输出【19 20 15 1 25 14 2 2 1 1】
     * @param money 总金额
     * @param peopleNum 总人数
     * @return
     */
    public static List<Integer> allocateMoney(double money, int peopleNum) {
        List<Integer> list = new ArrayList<>();
        double minMoney = min;
        double maxMoney = percentMax * money;
        int shareMoney = 0;
        double sum = 0;
        for (int i = 0; i < peopleNum; i++) {
            double max =  money - maxMoney * (peopleNum - i - 1);
            double min =  money - minMoney * (peopleNum - i - 1);
            minMoney = minMoney >max? minMoney :max;
            maxMoney = maxMoney < min ? maxMoney : min;
            shareMoney = (int) Math.floor((maxMoney - minMoney) * Math.random() + minMoney);
            money = money - shareMoney;
            sum += shareMoney;
            list.add(shareMoney);
        }
        System.out.println("要分配的红包总额为:" + sum + "元");
        return list;
    }




}
