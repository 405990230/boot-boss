package com.boss.javabasic.Duotai;

//子类--学生
public class Student extends Person{
    @Override
    public void say(){
        System.out.println("我是一个学生！");
    }


    public static void say2(){
        System.out.println("我是一个学生啊啊啊啊！");
    }

    public void done(){
        System.out.println("我在学习！");
    }
}

