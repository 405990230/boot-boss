package com.boss.design.creationMode.prototype;

import java.io.*;

/**
 * Created by yd on 2019/3/28.
 * 深克隆
 */
public class Student2 implements Serializable, Cloneable {
    private String name;
    private Subject2 subject2;

    public Student2(String name, Subject2 subject2) {
        this.name = name;
        this.subject2 = subject2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject2 getSubject2() {
        return subject2;
    }

    public void setSubject2(Subject2 subject2) {
        this.subject2 = subject2;
    }

    @Override
    public String toString() {
        return "name:" + name + "; subject:{" + subject2 + "}";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Student2 student2 = (Student2) super.clone();
        student2.subject2 = (Subject2) this.subject2.clone();
        return student2;
    }

    /* 深复制 二进制的写法，需要类序列化*/
    public Object deepClone() throws IOException, ClassNotFoundException {

        //使用序列化和反序列化实现深复制
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);
        byte[] bytes = bos.toByteArray();
        /* 读出二进制流产生的新对象 */
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Subject2 subject2 = new Subject2("100", "100");
        Student2 student2 = new Student2("YYY", subject2);
        System.out.println(student2);
        //方法一：对象内部所有引用型对象都进行clone。
        //Student2 st2 = (Student2)student2.clone();
        //方法二：对象序列化
        Student2 st2 = (Student2) student2.clone();
        st2.setName("ZZZ");
        st2.getSubject2().setChinese("99");
        System.out.println(student2);
        System.out.println(st2);
    }
}

class Subject2 implements Serializable, Cloneable {
    private String english;
    private String chinese;

    public Subject2(String chinese, String english) {
        this.chinese = chinese;
        this.english = english;
    }

    @Override
    public String toString() {
        return "english : " + english + "; chinese:" + chinese;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }
}