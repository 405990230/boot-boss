package com.boss.design.structuralModel.compositePattern;

import java.util.ArrayList;

/**
 * 3、树枝构件（Composite）角色：
 */
public class Dept implements Person {
    private String name;
    private ArrayList<Person> employees = new ArrayList<>();

    public Dept(String name) {
        this.name = name;
    }

    public void add(Person p) {
        employees.add(p);
    }

    public void remove(Person p) {
        employees.remove(p);
    }

    public Person getChild(int i) {
        return employees.get(i);
    }


    @Override
    public void describeEmployees() {
        System.out.println("部门：" + name);
        for (Person person : employees) {
            person.describeEmployees();
        }
    }

    @Override
    public double countMonthlyIncome() {
        int s = 0;
        for (Person person : employees) {
            s += person.countMonthlyIncome();
        }
        return s;
    }
}
