package com.boss.javabasic.Duotai;

//测试类
public class Test {
    public static void main(String[] args) {
        Person p = new Person();
        Student s= new Student();
        Teacher t = new Teacher();
        p.say();
        s.say();
        t.say();
        System.out.println("----------向上造型--继承-------------");
        Person pe = new Student();
        Person pe2 = new Teacher();
        pe.say();//动态绑定
        pe.say2();//静态绑定，在程序执行前方法已经被绑定，运行父类方法
        pe2.say();
        //点出来的是什么，看对象，能点出什么方法看类型
        System.out.println("--------向下造型--------");
        Student se = (Student)pe;
        se.say();
        se.say2();
        se.done();

//        Teacher te = (Teacher)pe;  //运行时报错，子类之间无法强转
//        te.say();
//		student se2 = (student)new person();//运行时报错
//		se2.say();//报错
    }
}


