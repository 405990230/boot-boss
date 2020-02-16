package com.boss.design.creationMode.prototype;

import java.io.Serializable;

/**
 * Created by yd on 2019/3/27.
 * 浅克隆
 */
public class Student implements Serializable, Cloneable {
    private String name;
    private Subject subject;

    public Student(String name, Subject subject) {
        this.name = name;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String toString() {
        return "name:" + name + "; subject:{" + subject + "}";
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Subject subject = new Subject("100", "100");
        Student student = new Student("YYY", subject);
        System.out.println(student);
        Student st = (Student) student.clone();
        st.setName("ZZZ");
        st.getSubject().setChinese("99");
        System.out.println(student);
        System.out.println(st);
    }
}

class Subject {
    private String english;
    private String chinese;

    public Subject(String chinese, String english) {
        this.chinese = chinese;
        this.english = english;
    }

    public String toString() {
        return "english : " + english + "; chinese:" + chinese;
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