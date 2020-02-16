package com.boss.javabasic.ali;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test3 {

    public static void main(String[] args) {
        System.out.println(digitCounts(11,1001));
        System.out.println(test(11,1001));
        System.out.println(testSplit(11,1001));
    }


    public static int test(int k,int n){
        int res = 0;
        if(n>=k){
            res = 1;
        }

        String str = ""+k;
        for(int i=k+1;i<=n;i++){
            String num= ""+i;
            res+=(getCount(num,str));

        }
        return res;
    }

    public static int getCount(String str,String key){
        int count = 0;
        int index = 0;
        while((index=str.indexOf(key,index))!=-1){
            index = index+key.length();
            count++;
        }
        return count;

    }


    public static int testSplit(int k,int n){
        int count = 0;
        int res = 0;
        if(n>=k){
            res = 1;
        }
        String str = ""+k;
        List<Integer> list = new ArrayList<>();
        for(int i=k+1;i<=n;i++){
            if((""+i).contains(str)){
                list.add(i);
                String[] strs = (""+i).split("");
                for(String string:strs){
                    if(string.equals(k+"")){
                        res++;
                    }
                }

            }
        }
        return res;
    }

    public static int digitCounts(int k,int n){
        int res = 0;
        for(int i=0;i<=n;i++){
            int cur = i;
            while(cur/10>0){
                int curIndex = cur%10;
                if(curIndex==k){
                    res++;
                    //System.out.println("curIndex:"+curIndex);
                }
                cur = cur/10;
            }
            if(cur == k){
                res++;
                //System.out.println("b"+i);
            }
        }
        return res;
    }
}
