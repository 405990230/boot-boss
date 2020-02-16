package com.boss.javabasic.Finally;

public class FinallyTest {
    public static void main(String[] args) {
        System.out.println(test());
    }

    public static int test(){
        int i =0;
        if(i==1){
            return 0;
        }
        //i = i/0;
        System.out.println("-------start--------");
        try {
            System.out.println("----try-------");
            //System.exit(0);
            return i;
        }finally {
            System.out.println("----finally-------");
            return 9;
        }
    }
}
